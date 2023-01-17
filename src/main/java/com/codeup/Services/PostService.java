package com.codeup.Services;

import com.codeup.entity.Post;

import java.util.List;

public interface PostService {
    // Save operation
    Post savePost(Post post);

    // Read operation
    List<Post> fetchPostList();

    // Update operation
    Post updatePost(Post post,
    Long id);

    // Delete operation
    void deletePostById(Long id);
}
