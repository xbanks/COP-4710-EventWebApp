package com.eventwebapp;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.entities.event.EventType;
import com.eventwebapp.entities.other.Location;
import com.eventwebapp.entities.rso.RSO;
import com.eventwebapp.entities.rso.RSOType;
import com.eventwebapp.entities.rso.RsoMember;
import com.eventwebapp.entities.rso.University;
import com.eventwebapp.entities.users.*;
import com.eventwebapp.repositories.*;
import com.eventwebapp.security.WebSecurityConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class EventwebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventwebappApplication.class, args);
    }

    @Bean
    CommandLineRunner runner (AdminRepo adminRepo, UserRepo userRepo, StudentRepo studentRepo,
                              LocationRepo locationRepo, SuperAdminRepo superAdminRepo,
                              UniversityRepo universityRepo, RSORepo rsoRepo,
                              EventRepo eventRepo, EventTypeRepo eventTypeRepo,
                              RsoMemberRepo rsoMemberRepo, UserRoleRepo userRoleRepo,
                              WebSecurityConfig webSecurityConfig, RSOTypeRepo rsoTypeRepo){
        return args -> {

            // New User, Super Admin,
            String passwd = webSecurityConfig.passwordEncoder().encode("password1");
            Long userId = userRepo.save(new User("John", "Johnson", passwd, "test@mail.mail", "5615555555")).getId_user();

            // new super user
            superAdminRepo.save(new SuperAdmin(userId));
            userRoleRepo.save(new UserRole(userId, UserRole.SADMIN));
            userRoleRepo.save(new UserRole(userId, UserRole.STUDENT));
            userRoleRepo.save(new UserRole(userId, UserRole.ADMIN));

            // new location for the university
            Long locId = locationRepo.save(new Location("UCF", 28.6024321F, -81.2022486F)).getId_location();

            // new university
            Long uniId = universityRepo.save(new University("University of Central FL", "Orlandos one and only", locId, 55000L, "UCF")).getId_university();

            rsoTypeRepo.save(new RSOType(1L, "Recreational"));

            // new rso
            Long rsoId = rsoRepo.save(new RSO("Kendo Club", 1L, "The koolest kendo klub around!", 10L, 1L)).getId_rso();


            // new admin that owns previous rso
            adminRepo.save(new Admin(userId, rsoId));
            studentRepo.save(new Student(userId, uniId, "xavier@knights.ucf.edu"));

            rsoMemberRepo.save(new RsoMember(rsoId, userId));

            Long publicID = eventTypeRepo.save(new EventType(1L, "public")).getId_event_type();
            Long privateID = eventTypeRepo.save(new EventType(2L, "private")).getId_event_type();
            Long rsoID = eventTypeRepo.save(new EventType(3L, "rso")).getId_event_type();

            Long eventId = eventRepo.save(new Event(new Date(2016, 7, 7), LocalTime.now().plusHours(5).plusMinutes(30), locId, "This is a test event for the Kendo Club", false, false, publicID, rsoId, "Test Event", "Xavier Banks", "xavier@email.com", "5612945725")).getId_event();
            Long eventId2 = eventRepo.save(new Event(new Date(2016, 7, 7), LocalTime.now().plusHours(12).plusMinutes(25), locId, "This is the second event for the Kendo Club", false, false, publicID, rsoId, "Event Number 2", "Myra Banks", "myra@email.com", "5612945755")).getId_event();

            userRoleRepo.findByUser(userId).stream().forEach(System.out::println);
        };
    }
}
