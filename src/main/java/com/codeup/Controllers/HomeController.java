package com.codeup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "This is the landing page!";
    }
}
