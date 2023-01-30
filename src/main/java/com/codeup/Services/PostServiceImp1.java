package com.codeup.Services;

import com.codeup.entity.Post;
import com.codeup.repository.PostRepository;

import java.util.List;
import java.util.Objects;

import com.codeup.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class PostServiceImp1 implements PostService{

    private PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImp1(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findPostByUserId(long id) {
        return null;
    }

    @Override
    public List<Post> findPostsByUserId(long id) {
        return postRepository.findPostsByUserId(id);

    }
    @Override
    public int updateTitleAndBodyById(String title, String body, long id) {
        return postRepository.updateTitleAndBodyById(title, body, id);
    }

    @Override
    public List<Post> fetchPostList() {

        return (List<Post>) postRepository.findAll();

    }

    @Override
    public Post updatePost(Post post, Long id) {
        Post postDB
                =  postRepository.getById(id);

        if (Objects.nonNull(post.getTitle())
                && !"".equalsIgnoreCase(
                post.getTitle())) {
            postDB.setTitle(
                    post.getTitle());
        }

        if (Objects.nonNull(
                post.getBody())
                && !"".equalsIgnoreCase(
                post.getBody())) {
            postDB.setBody(
                    post.getBody());
        }

        if (Objects.nonNull(
                userRepository.getById(post.getUser().getId()))) {
            postDB.setUser(userRepository.getById(post.getUser().getId()));
        }


        return postRepository.save(postDB);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
