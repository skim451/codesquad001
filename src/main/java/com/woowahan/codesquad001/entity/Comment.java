package com.woowahan.codesquad001.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "comment_to_question"))
    private Question question;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "comment_to_user"))
    private User author;

    private String content;
}
