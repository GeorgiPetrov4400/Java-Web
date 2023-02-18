package com.likebookapp.model.dto;

import com.likebookapp.model.entity.MoodNameEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostAddDTO {

    @NotBlank
    @Size(min = 2, max = 150)
    private String content;

    @NotNull
    private MoodNameEnum mood;

    public PostAddDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MoodNameEnum getMood() {
        return mood;
    }

    public void setMood(MoodNameEnum mood) {
        this.mood = mood;
    }
}
