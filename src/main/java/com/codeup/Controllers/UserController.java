package com.codeup.Controllers;

import com.codeup.Services.UserService;
import com.codeup.entity.Post;
import com.codeup.entity.User;
import com.codeup.entity.UserWithRoles;
import com.codeup.repository.PostRepository;
import com.codeup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository usersDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PostRepository postRepository;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    String sign(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }
    @PostMapping("/register")
    String up(@ModelAttribute User user) {
        String pw = passwordEncoder.encode(user.getPassword());
        user.setPassword(pw);
        usersDao.save(user);
        return "redirect:/posts/show";
    }
    @GetMapping("/login")
    String login(@ModelAttribute User user) {

        return "login";
    }
//
//    @PostMapping("/login")
//    String loggedin(@ModelAttribute User user) {
//        User userDb = usersDao.findByUsername(user.getUsername());
//        System.out.println(user);
//        if (userDb == null) {
//            return "redirect:/login";
//        }
//        if (passwordEncoder.matches(user.getPassword(), userDb.getPassword())){
//            return "redirect:/posts";
//        }
//        return "redirect:/login";
//    }
//    @GetMapping("/login?logout")
//        String logout() {
//            SecurityContextHolder.clearContext();
//            return "redirect:/login";
//        }
    @GetMapping("/profile")
    String profile(Model model) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "redirect:/login";
        } else {
            UserWithRoles user = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(user);
            User user1 = usersDao.findByUsername(user.getUsername());
            List<Post> posts = postRepository.findPostsByUserId(user1.getId());
            model.addAttribute("posts", posts);
            return "/profile";
        }

    }
}
