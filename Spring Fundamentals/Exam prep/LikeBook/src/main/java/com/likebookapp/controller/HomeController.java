package com.likebookapp.controller;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.service.PostService;
import com.likebookapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final PostService postService;

    public HomeController(CurrentUser currentUser, PostService postService) {
        this.currentUser = currentUser;
        this.postService = postService;
    }

    @GetMapping("/home")
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "redirect:/";
        }

        long currentUserId = currentUser.getId();

        List<PostDTO> ownPosts = this.postService.getPostsOwnedBy(currentUserId);
        List<PostDTO> otherPosts = this.postService.getPostsNotOwnedBy(currentUserId);

        model.addAttribute("ownPosts", ownPosts);
        model.addAttribute("otherPosts", otherPosts);

        return "home";
    }
}
