package com.example.vibeapp.post;

import java.time.LocalDateTime;

public class PostListDto {
    private Long no;
    private String title;
    private LocalDateTime createdAt;
    private int views;

    public PostListDto() {}

    public PostListDto(Long no, String title, LocalDateTime createdAt, int views) {
        this.no = no;
        this.title = title;
        this.createdAt = createdAt;
        this.views = views;
    }

    public static PostListDto from(Post entity) {
        return new PostListDto(
            entity.getNo(),
            entity.getTitle(),
            entity.getCreatedAt(),
            entity.getViews()
        );
    }

    // Getters
    public Long getNo() { return no; }
    public String getTitle() { return title; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getViews() { return views; }
}
