package ru.yurkaishere.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/help")
    public String about(Model model) {
        model.addAttribute("title", "Help");
        return "/help";
    }

}