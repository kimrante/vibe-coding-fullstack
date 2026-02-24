package com.example.vibeapp.post;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getPosts(int page, int size) {
        List<Post> allPosts = postRepository.findAll();
        int start = (page - 1) * size;
        int end = Math.min(start + size, allPosts.size());
        
        if (start > allPosts.size()) {
            return List.of();
        }
        
        return allPosts.subList(start, end).stream()
                .map(PostListDto::from)
                .toList();
    }

    public PostResponseDto getPost(Long no) {
        return postRepository.findById(no)
                .map(PostResponseDto::from)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
    }

    public void createPost(PostCreateDto createDto) {
        Long nextNo = postRepository.findAll().stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;
        
        postRepository.save(createDto.toEntity(nextNo));
    }

    public void updatePost(Long no, PostUpdateDto updateDto) {
        Post post = postRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        post.setTitle(updateDto.getTitle());
        post.setContent(updateDto.getContent());
        post.setUpdatedAt(LocalDateTime.now());
    }

    public void deletePost(Long no) {
        postRepository.deleteById(no);
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.findAll().size();
        return (int) Math.ceil((double) totalPosts / size);
    }
}
