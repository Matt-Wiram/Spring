package com.codeup.Controllers;
import com.codeup.Services.EmailServiceslmp1;
import com.codeup.entity.Post;
import com.codeup.entity.User;
import com.codeup.entity.UserWithRoles;
import com.codeup.repository.PostRepository;
import com.codeup.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class PostController {
    private final EmailServiceslmp1 emailService;
    private final PostRepository p;

    private final UserRepository userRepository;



    public PostController(PostRepository p,
                          UserRepository userRepository, EmailServiceslmp1 emailService) {
        this.p = p;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @GetMapping("/posts/edit")
    public String edit(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        List<Post> posts = p.findPostsByUserId(user.getId());
        model.addAttribute("posts", posts);
        return "posts/edit";
    }
    @PostMapping("/posts/edit")
    public String edited(@RequestParam("title") String title, @RequestParam("body") String body, @RequestParam(name = "id") long id) {

        Post post = p.getById(id);
        post.setBody(body);
        post.setTitle(title);
        p.save(post);
        return "redirect:/posts/show";
    }

    @GetMapping("/posts/show")
    public String show(Model model) {

        UserWithRoles user = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Post> posts = p.findPostsByUserId(user.getId());
        model.addAttribute("posts", posts);

        return "posts/show";
    }

    @GetMapping("/posts/creat")
    public String index(Model model) {


        model.addAttribute("ads", p.findAll());
        return "posts/creat";
    }

    @PostMapping(path = "posts/creat")
    String posts(Model model, @RequestParam(name = "title") Optional<String> title, @RequestParam(name = "body") Optional<String> body, @RequestParam(name = "idd") Optional<Long> id) throws IOException {
        if (id.isEmpty()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Post post = new Post(title.get(), body.get(), user);
            p.save(post);
            emailService.prepareAndSend(post, "You sent a new post", post.getBody());
            model.addAttribute("post", post);
        } else {

            p.deleteById(id.get());
        }

        return "redirect:/profile";


    }




    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody

    public String addOne(Model model) {

        List<Post> posts = p.findAll();
        model.addAttribute("id", posts);
        return p.getById(Long.valueOf(24)).getBody();
    }
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String addOn(@PathVariable String id, Model model) {
        Post post = p.getById(Long.valueOf(id));
        model.addAttribute(post);
        return post.toString();
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
    void id(@RequestParam(name = "id") String idd, Model model ) throws Exception{
        System.out.println(idd);
        model.addAttribute("id", idd);
        System.out.println(idd);


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

//
//    @PostMapping(path="/posts/create")
//    @ResponseBody
//    public String getName(
//            @RequestParam(name = "name") String name,@RequestParam(name="input") String input, Model model) {
//        name += "123";
//        model.addAttribute(name);
//        model.addAttribute(input);
//
//        return name + input;
//    }


}


