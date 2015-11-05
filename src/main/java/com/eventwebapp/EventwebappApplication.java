package com.eventwebapp;

import com.eventwebapp.entities.event.Event;
import com.eventwebapp.entities.event.EventType;
import com.eventwebapp.entities.other.Location;
import com.eventwebapp.entities.rso.RSO;
import com.eventwebapp.entities.rso.University;
import com.eventwebapp.entities.users.Admin;
import com.eventwebapp.entities.users.SuperAdmin;
import com.eventwebapp.entities.users.User;
import com.eventwebapp.repositories.*;
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
                              EventRepo eventRepo, EventTypeRepo eventTypeRepo){
        return args -> {

            // New User, Super Admin,
            Long userId = userRepo.save(new User("xavier", "banks", "password1", "xavier@mail.mail", "5615555555")).getId_user();

            // new super user
            superAdminRepo.save(new SuperAdmin(userId));

            // new location for the university
            Long locId = locationRepo.save(new Location("UCF", 28.6024321F, -81.2022486F)).getId_location();

            // new university
            universityRepo.save(new University("University of Central FL", "Orlandos one and only", locId, 55000L, "UCF"));

            // new rso
            Long rsoId = rsoRepo.save(new RSO("Kendo Club", 1L, 10, 1L)).getId_rso();

            // new admin that owns previous rso
            adminRepo.save(new Admin(userId, rsoId));

            Long publicID = eventTypeRepo.save(new EventType(1L, "public")).getId_event_type();
            Long privateID = eventTypeRepo.save(new EventType(2L, "private")).getId_event_type();
            Long rsoID = eventTypeRepo.save(new EventType(3L, "rso")).getId_event_type();

            Long eventId = eventRepo.save(new Event(new Date(2016, 7, 7), LocalTime.now().plusHours(5).plusMinutes(30), locId, "This is a test event for the Kendo Club", false, false, publicID, rsoId, "Test Event")).getId_event();
            Long eventId2 = eventRepo.save(new Event(new Date(2016, 7, 7), LocalTime.now().plusHours(12).plusMinutes(25), locId, "This is the second event for the Kendo Club", false, false, publicID, rsoId, "Event Number 2")).getId_event();
        };
    }
}
