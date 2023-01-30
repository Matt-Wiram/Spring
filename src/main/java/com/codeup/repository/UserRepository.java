package com.codeup.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codeup.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getById(long id);
    User findByUsername(String name);
}
