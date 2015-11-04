package com.eventwebapp.repositories;

import com.eventwebapp.entities.other.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface EntityTypeRepo extends JpaRepository<EntityType, Long> {
}
