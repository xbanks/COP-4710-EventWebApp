package com.eventwebapp.repositories;

import com.eventwebapp.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Xavier on 11/2/2015.
 */

//@RepositoryRestResource(exported = false)
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    User findByUsername(String username);
}
