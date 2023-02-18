package com.likebookapp.service;

import com.likebookapp.model.dto.PostDTO;
import com.likebookapp.model.service.PostServiceModel;

import java.util.List;

public interface PostService {
    void addPost(PostServiceModel postServiceModel);

    List<PostDTO> getPostsOwnedBy(long ownerId);

    List<PostDTO> getPostsNotOwnedBy(long ownerId);

    void likePost(Long postId, Long currentUserId);

    void removePostById(Long id);
}
