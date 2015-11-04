package com.eventwebapp.repositories;

import com.eventwebapp.entities.rso.RSO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Xavier on 11/2/2015.
 */

@RepositoryRestResource
public interface RSORepo extends JpaRepository<RSO, Long> {
}
