package com.codeup.Services;

import com.codeup.entity.Post;
import com.codeup.repository.PostRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import com.codeup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
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
