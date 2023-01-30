package com.codeup.Services;

import com.codeup.entity.Post;

import java.util.List;

public interface PostService {
    // Save operation
    Post savePost(Post post);
    List<Post> findPostByUserId (long id);

    List<Post> findPostsByUserId(long id);

    int updateTitleAndBodyById(String title, String body, long id);

    // Read operation
    List<Post> fetchPostList();

    // Update operation
    Post updatePost(Post post,
    Long id);

    // Delete operation
    void deletePostById(Long id);
}
