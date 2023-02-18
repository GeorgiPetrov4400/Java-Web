package com.likebookapp.service.impl;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.model.service.PostServiceModel;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.service.MoodService;
import com.likebookapp.service.PostService;
import com.likebookapp.service.UserService;
import com.likebookapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MoodService moodService;
    private final CurrentUser currentUser;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserService userService, MoodService moodService, CurrentUser currentUser) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.moodService = moodService;
        this.currentUser = currentUser;
    }

    @Override
    public void addPost(PostServiceModel postServiceModel) {
        Post post = modelMapper.map(postServiceModel, Post.class);
        post.setUser(userService.findById(currentUser.getId()));
        post.setMood(moodService.findByMoodNameEnum(postServiceModel.getMood()));

        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getPostsOwnedBy(long ownerId) {
        return this.postRepository.findByUserId(ownerId).stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
//                .map(postDTO -> postDTO.setAllLikes(postDTO.getUserLikes().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostsNotOwnedBy(long ownerId) {
        return this.postRepository.findByUserIdNot(ownerId).stream()
                .map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void likePost(Long postId, Long currentUserId) {
        Post post = postRepository.findById(postId).orElse(null);

        User user = userService.findById(currentUserId);

        post.getUserLikes().add(user);
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    @Override
    public void removePostById(Long id) {
        postRepository.deleteById(id);
    }
}
