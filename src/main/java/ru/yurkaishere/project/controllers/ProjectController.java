package ru.yurkaishere.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yurkaishere.project.models.Post;
import ru.yurkaishere.project.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    private final PostRepository postRepository;

    @Autowired
    public ProjectController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/project")
    public String projectMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "project-main";
    }

    @GetMapping("/project/add")
    public String projectAdd(Model model) {
        return "project-add";
    }

    @PostMapping("/project/add")
    public String ProjectPostAdd(@RequestParam String title, @RequestParam String anons,
                                 @RequestParam String text, Model model) {
        Post post = new Post(title, anons, text);
        postRepository.save(post);
        return "redirect:/project";
    }

    @GetMapping("/project/{id}")
    public String projectDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/project";
        }

        Optional<Post> post = postRepository.findById(id);
        List<Post> result = new ArrayList<>();
        post.ifPresent(result :: add);
        model.addAttribute("post", result);
        return "project-details";
    }

    @GetMapping("/project/{id}/edit")
    public String projectEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/project";
        }

        Optional<Post> post = postRepository.findById(id);
        List<Post> result = new ArrayList<>();
        post.ifPresent(result :: add);
        model.addAttribute("post", result);
        return "project-edit";
    }

    @PostMapping("/project/{id}/edit")
    public String ProjectPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons,
                                 @RequestParam String text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setText(text);
        postRepository.save(post);
        return "redirect:/project";
    }

    @PostMapping("/project/{id}/remove")
    public String ProjectPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/project";
    }
}
