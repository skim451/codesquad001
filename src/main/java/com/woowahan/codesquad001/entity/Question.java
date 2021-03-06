package com.woowahan.codesquad001.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "question_to_user"))
    private User author;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    private String content;

    private boolean deleted = false;

    public Question() {

    }

    public int getCommentCount() {
        return comments.size();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDateTime) {
        this.createdDate = createdDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", createdDateTime=" + createdDate +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
