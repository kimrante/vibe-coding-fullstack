package com.example.vibeapp.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        model.addAttribute("posts", postService.getPosts(page, pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postService.getTotalPages(pageSize));
        return "post/posts";
    }

    @GetMapping("/posts/new")
    public String createForm() {
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String create(@RequestParam String title,
                         @RequestParam String content) {
        postService.createPost(title, content);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}")
    public String view(@PathVariable Long no, Model model) {
        model.addAttribute("post", postService.getPost(no));
        return "post/post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String updateForm(@PathVariable Long no, Model model) {
        model.addAttribute("post", postService.getPost(no));
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable Long no,
                         @RequestParam String title,
                         @RequestParam String content) {
        postService.updatePost(no, title, content);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/{no}/delete")
    public String remove(@PathVariable Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
