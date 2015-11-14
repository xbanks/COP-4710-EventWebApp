package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.event=:event")
    List<Comment> findByEvent(@Param("event") Long event);
}
