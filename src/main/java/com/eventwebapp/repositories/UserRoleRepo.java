package com.eventwebapp.repositories;

import com.eventwebapp.entities.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
}
