package com.example.vibeapp.post;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String createForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String create(@Valid @ModelAttribute PostCreateDto postCreateDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.createPost(postCreateDto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}")
    public String view(@PathVariable Long no, Model model) {
        model.addAttribute("post", postService.getPost(no));
        return "post/post_detail";
    }

    @GetMapping("/posts/{no}/edit")
    public String updateForm(@PathVariable Long no, Model model) {
        PostResponseDto post = postService.getPost(no);
        model.addAttribute("postUpdateDto", new PostUpdateDto(post.getTitle(), post.getContent()));
        model.addAttribute("no", no);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable Long no,
                         @Valid @ModelAttribute PostUpdateDto postUpdateDto,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("no", no);
            return "post/post_edit_form";
        }
        postService.updatePost(no, postUpdateDto);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/{no}/delete")
    public String remove(@PathVariable Long no) {
        postService.deletePost(no);
        return "redirect:/posts";
    }
}
