package com.eventwebapp.repositories;

import com.eventwebapp.entities.other.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface LocationRepo extends JpaRepository<Location, Long> {
    Location findByPlaceId(String placeId);
}
