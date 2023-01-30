package com.codeup.repository;

import com.codeup.entity.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying
    @Query(value = "update posts set title = :title,  body = :body where id = :id" , nativeQuery = true)
    int updateTitleAndBodyById(String title, String body, long id);
    Post getById(Long id);

    @Query(value = "Select * from posts where user_id = :id", nativeQuery = true)
    List<Post> findPostByUserId ( long id);

    List<Post> findPostsByUserId(long id);
//    Post save(Post post);
//
//    List<Post> findAll();
//    
//    Post getById(Long id);
//
//    void deleteById(Long id);
}


