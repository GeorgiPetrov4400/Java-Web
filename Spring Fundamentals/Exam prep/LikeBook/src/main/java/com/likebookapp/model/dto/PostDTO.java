package com.likebookapp.model.dto;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.User;

import java.util.Set;

public class PostDTO {

    private long id;

    private String content;

    private User user;

    private int allLikes;

    private Set<User> userLikes;

    private Mood mood;

    public PostDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public int getAllLikes() {
        return allLikes;
    }

    public void setAllLikes(int allLikes) {
        this.allLikes = allLikes;
    }
}
