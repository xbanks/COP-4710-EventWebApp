package com.eventwebapp.repositories;

import com.eventwebapp.entities.other.PictureMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface PictureMappingRepo extends JpaRepository<PictureMapping, Long> {
}
