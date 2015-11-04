package com.eventwebapp.repositories;

import com.eventwebapp.entities.users.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
}
