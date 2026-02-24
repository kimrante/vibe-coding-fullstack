package com.example.vibeapp.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PostCreateDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
    private String title;

    private String content;

    public PostCreateDto() {}

    public PostCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(Long nextNo) {
        return new Post(
            nextNo,
            this.title,
            this.content,
            LocalDateTime.now(),
            LocalDateTime.now(),
            0
        );
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
