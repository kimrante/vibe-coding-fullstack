package com.example.vibeapp.controller;

import com.example.vibeapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts";
    }

    @GetMapping("/posts/new")
    public String newForm() {
        return "post_new_form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/add")
    public String add(@org.springframework.web.bind.annotation.RequestParam String title,
                      @org.springframework.web.bind.annotation.RequestParam String content) {
        postService.addPost(title, content);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@org.springframework.web.bind.annotation.PathVariable Long no, Model model) {
        model.addAttribute("post", postService.getPostByNo(no));
        return "post_detail";
    }
}
