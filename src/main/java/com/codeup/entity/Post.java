package com.codeup.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.InfoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts")

public class Post  {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

    @Column(nullable = false, length = 100)
        private String title;

    @Column(nullable = false, length = 100)
        private String body;


    @OneToOne
    private User user;

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
