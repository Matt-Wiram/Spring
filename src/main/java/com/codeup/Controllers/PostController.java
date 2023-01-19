package com.codeup.Controllers;
import com.codeup.entity.Post;
import com.codeup.entity.User;
import com.codeup.repository.PostRepository;
import com.codeup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.codeup.Services.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller

public class PostController {

    private final PostRepository p;
    private final UserRepository userRepository;

    public PostController(PostRepository p,
                          UserRepository userRepository) {
        this.p = p;
        this.userRepository = userRepository;
    }

    @GetMapping("/show")
    public String show(HttpServletRequest httpServletRequest, Model model) {
        Post post = (Post)httpServletRequest.getSession().getAttribute("post");
        User user = userRepository.getById(post.getUser().getId());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("posts", post.getBody());
        return "posts/show";
    }

    @GetMapping("/posts/creat")
    public String index(Model model) {


        model.addAttribute("ads", p.findAll());
        return "posts/create";
    }

    @PostMapping(path = "/posts/creat")
    void posts(Model model, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, HttpServletResponse servletResponse, HttpServletRequest httpServletRequest) throws IOException {
            User user = userRepository.getById(1);
            Post post = new Post(title, body, user);
            p.save(post);
            model.addAttribute("post", post);


        httpServletRequest.getSession().setAttribute("post", post);
        servletResponse.sendRedirect("/show");

    }




    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody

    public String addOne() {
        return "null and void";
    }
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String addOn(@PathVariable String id) {
        return id;
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String addO() {
        return "page";
    }

    @RequestMapping(path = "/posts/created", method = RequestMethod.GET)
    @ResponseBody
    public String addO1(String that) {
        return "<p>"+ that +"34</p>";
    }
    @RequestMapping(path="/roll/dice", method = RequestMethod.GET)
    public  String roll(Model model){
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int id = i + 1;

            ids.add(id);


        }

        model.addAttribute("id", ids);
        return "button";
    }

    @PostMapping(path="/roll/dice")
    void id(@RequestParam(name = "id") String idd, HttpServletResponse httpResponse, Model model ) throws Exception{
        System.out.println(idd);
        model.addAttribute("id", idd);
        System.out.println(idd);
        httpResponse.sendRedirect("/roll/" + idd);

    }
    @RequestMapping(path = "/roll/{n}", method = RequestMethod.GET)

    String ret(@PathVariable String n, Model model ) {
        int rando = (int) Math.floor(Math.random() * 6 + 1);
        boolean check = String.valueOf(rando).equals(n);
        String win;
        if (check) {
            win = "You won the game";
        } else {
            win = "You lost the game";
        }
        model.addAttribute("check", win);
        model.addAttribute("id", String.valueOf(rando));
        model.addAttribute("n", n);
        return "check";
    }


    @PostMapping(path="/posts/create")
    @ResponseBody
    public String getName(
            @RequestParam(name = "name") String name,@RequestParam(name="input") String input, Model model) {
        name += "123";
        model.addAttribute(name);
        model.addAttribute(input);

        return name + input;
    }


}


