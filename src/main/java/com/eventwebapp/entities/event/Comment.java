package com.eventwebapp.entities.event;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Xavier on 11/2/2015.
 */

@Entity
@Table(name = "comment")
public class Comment  {

    @Id
    @GeneratedValue
    Long id_comment;

    @NotNull
    Long commenter;

    @NotNull
    Long event;

    @GeneratedValue
    Date timestamp;

    @NotEmpty
    @Size(min = 1, max = 140, message = "Comments Limited to 140 characters")
    String content;

    public Comment() {
    }

    public Comment(String content, Long commenter, Long event) {
        this.content = content;
        this.commenter = commenter;
        this.event = event;
    }

    public Comment ValidateAndCreate(String content, Long commenter, Long event) {
        if(content == null || commenter == null || event == null){
            return null;
        }

        return new Comment(content, commenter, event);
    }

    public Long getId_comment() {
        return id_comment;
    }

    public void setId_comment(Long id_comment) {
        this.id_comment = id_comment;
    }

    public Long getCommenter() {
        return commenter;
    }

    public void setCommenter(Long commenter) {
        this.commenter = commenter;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id_comment=" + id_comment +
                ", commenter=" + commenter +
                ", event=" + event +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
