package com.codeup.Services;

import com.codeup.entity.Post;
import com.codeup.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    // Read operation
    List<User> fetchUserList();

    // Update operation
    User updateUser(User user,
                    Long id);

    // Delete operation
    void deleteUserById(Long id);

    User findByUsername(String username);
}
