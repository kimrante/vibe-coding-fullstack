package com.example.vibeapp.post;

import java.time.LocalDateTime;

public class PostResponseDto {
    private Long no;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int views;

    public PostResponseDto() {}

    public PostResponseDto(Long no, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, int views) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.views = views;
    }

    public static PostResponseDto from(Post entity) {
        return new PostResponseDto(
            entity.getNo(),
            entity.getTitle(),
            entity.getContent(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getViews()
        );
    }

    // Getters
    public Long getNo() { return no; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public int getViews() { return views; }
}
