package com.eventwebapp.repositories;

import com.eventwebapp.entities.users.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface UserTypeRepo extends JpaRepository<UserType, Long> {
}
