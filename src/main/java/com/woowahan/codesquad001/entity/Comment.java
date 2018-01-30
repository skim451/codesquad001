package com.woowahan.codesquad001.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private User author;

    private String content;

    private boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public long getId() {
        return id;
    }

    public Comment setId(long id) {
        this.id = id;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public Comment setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Comment setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Comment setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Comment setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", question=" + question +
                ", createdDate=" + createdDate +
                ", content='" + content + '\'' +
                '}';
    }
}
