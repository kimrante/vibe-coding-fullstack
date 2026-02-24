package com.example.vibeapp.post;

import java.time.LocalDateTime;

public record PostResponseDto(
    Long no,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    int views
) {
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
}
