package com.example.vibeapp.service;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostByNo(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        
        // 조회수 증가
        post.setViews(post.getViews() + 1);
        
        return post;
    }

    public void addPost(String title, String content) {
        long nextNo = postRepository.findAll().stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;

        Post post = new Post();
        post.setNo(nextNo);
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(java.time.LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);

        postRepository.save(post);
    }

    public void updatePost(Long no, String title, String content) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(java.time.LocalDateTime.now());
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }

    public List<Post> getPostsByPage(int page, int size) {
        List<Post> allPosts = postRepository.findAll();
        int start = (page - 1) * size;
        int end = Math.min(start + size, allPosts.size());
        
        if (start > allPosts.size()) {
            return List.of();
        }
        
        return allPosts.subList(start, end);
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.findAll().size();
        return (int) Math.ceil((double) totalPosts / size);
    }
}
