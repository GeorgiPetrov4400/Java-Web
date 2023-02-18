package com.likebookapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private MoodNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Mood() {
    }

    public MoodNameEnum getName() {
        return name;
    }

    public void setName(MoodNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
