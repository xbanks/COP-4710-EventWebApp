package com.eventwebapp.repositories;

import com.eventwebapp.entities.users.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

    @Query("SELECT r.role FROM UserRole r WHERE r.user=:user")
    List<String> findByUser(@Param("user") Long user);
}
