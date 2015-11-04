package com.eventwebapp.repositories;

import com.eventwebapp.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
