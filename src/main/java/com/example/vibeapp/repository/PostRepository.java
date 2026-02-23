package com.example.vibeapp.repository;

import com.example.vibeapp.domain.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        // 초기 예제 데이터 10개 추가
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(
                i,
                "Vibe Coding 게시글 제목 " + i,
                "이것은 " + i + "번째 게시글의 상세 내용입니다. 삼성 스타일의 깔끔한 코딩을 경험하세요.",
                LocalDateTime.now().minusDays(10 - i),
                LocalDateTime.now().minusDays(10 - i),
                (int) (Math.random() * 1000)
            ));
        }
    }

    public List<Post> findAll() {
        return posts.stream()
                .sorted((p1, p2) -> p2.getNo().compareTo(p1.getNo()))
                .toList();
    }

    public Optional<Post> findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst();
    }

    public void save(Post post) {
        posts.add(post);
    }
}
