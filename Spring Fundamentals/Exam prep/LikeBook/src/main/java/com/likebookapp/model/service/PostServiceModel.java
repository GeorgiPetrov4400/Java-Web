package com.likebookapp.model.service;

import com.likebookapp.model.entity.MoodNameEnum;
import com.likebookapp.model.entity.User;

import java.util.Set;

public class PostServiceModel {

    private Long id;

    private String content;

    private User user;

    private Set<User> userLikes;

    private MoodNameEnum mood;

    public PostServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public MoodNameEnum getMood() {
        return mood;
    }

    public void setMood(MoodNameEnum mood) {
        this.mood = mood;
    }
}
