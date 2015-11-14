package com.eventwebapp.security;

import com.eventwebapp.entities.users.User;
import com.eventwebapp.repositories.UserRepo;
import com.eventwebapp.repositories.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xavier on 11/11/15.
 */

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo, UserRoleRepo userRoleRepo) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        s = s.toLowerCase();
        User user = userRepo.findByEmail(s);

        System.out.println("Looking for " + s);

        if(user == null){
            System.out.println(s + " is NULL");
            throw new UsernameNotFoundException("No user present with the email: " + s);
        }else {
            List<String> userRoles = userRoleRepo.findByUser(user.getId_user());

            System.out.println(s + " has user roles " + userRoles.toString() );
            return new CustomUserDetails(user, userRoles);
        }
    }
}
