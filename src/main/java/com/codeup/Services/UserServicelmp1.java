package com.codeup.Services;

import com.codeup.entity.User;
import com.codeup.entity.User;
import com.codeup.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServicelmp1 implements UserService {
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }



    @Override
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long id) {
        User userDB
                =  userRepository.getById(id);

        if (Objects.nonNull(user.getEmail())
                && !"".equalsIgnoreCase(
                user.getEmail())) {
            userDB.setEmail(
                    user.getEmail());
        }

        if (Objects.nonNull(
                user.getUsername())
                && !"".equalsIgnoreCase(
                user.getUsername())) {
            userDB.setUsername(
                    user.getUsername());
        }
        if (Objects.nonNull(
                user.getPassword())
        && !"".equalsIgnoreCase(
                user.getPassword())) {
            userDB.setPassword(
                    user.getPassword()
            );
        }



        return userRepository.save(userDB);
    }

    @Override
    public void deleteUserById(Long id) {
            userRepository.deleteById(id);
    }
}
