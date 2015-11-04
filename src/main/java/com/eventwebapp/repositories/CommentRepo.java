package com.eventwebapp.repositories;

import com.eventwebapp.entities.event.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Xavier on 11/2/2015.
 */
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
