package com.likebookapp.controller;

import com.likebookapp.model.dto.PostAddDTO;
import com.likebookapp.model.service.PostServiceModel;
import com.likebookapp.service.PostService;
import com.likebookapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public PostController(PostService postService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.postService = postService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public PostAddDTO postAddDTO() {
        return new PostAddDTO();
    }

    @GetMapping("/add")
    public String add() {
        if (currentUser.getId() == null) {
            return "redirect:/users/login";
        }

        return "post-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid PostAddDTO postAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAddDTO", postAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.postAddDTO",
                            bindingResult);

            return "redirect:add";
        }

        postService.addPost(modelMapper.map(postAddDTO, PostServiceModel.class));

        return "redirect:/home";
    }

    @GetMapping("like-post/{id}")
    public String likePost(@PathVariable Long id) {
        postService.likePost(id, currentUser.getId());
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removePost(@PathVariable Long id) {
        postService.removePostById(id);

        return "redirect:/home";
    }
}
