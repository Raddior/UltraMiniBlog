package com.example.demo.controller;


import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String commentPage(HttpSession session, Model model) {

        String username = (String) session.getAttribute("name");

        if (username != null) {
            model.addAttribute("name", username);
        } else {
            model.addAttribute("name", "Анонім");
        }


        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);

        return "comments";
    }

    @PostMapping
    public String postComment(@RequestParam String comment, HttpSession session, Model model) {

        String username = (String) session.getAttribute("name");

        User user = userService.findByName(username);

        Comment newComment = new Comment();
        newComment.setContent(comment);
        newComment.setUser(user);
        commentService.addComment(newComment);

        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        model.addAttribute("name", username);
        model.addAttribute("comment", comment);

        return "comments";
    }
}