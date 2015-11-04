package com.eventwebapp.repositories;

import com.eventwebapp.entities.rso.RSOType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface RSOTypeRepo extends JpaRepository<RSOType, Long> {
}
