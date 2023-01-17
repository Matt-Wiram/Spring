package com.codeup.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class MathController {
//    @RequestMapping(path = "/add/{num1}/add/{num2}", method = RequestMethod.GET)
//    @ResponseBody
//    public String addOne(@PathVariable int num1, int num2) {
//        return num1 + num2 + "!";
//    }
@RequestMapping(path = "/add/{number}/add/{number2}", method = RequestMethod.GET)
@ResponseBody
public String addOne(@PathVariable int number, @PathVariable int number2) {
    return String.valueOf(number + number2);
}
}
