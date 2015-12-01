package com.eventwebapp.util;

import com.eventwebapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xavier on 11/22/15.
 */
@Service
public class Utility {

    @Autowired
    UserRepo userRepo;

    public Long emailToId(String email){
        return userRepo.findByEmail(email).getId_user();
    }
}
