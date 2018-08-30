package org.softuni.secondtech.controllers;

import org.softuni.secondtech.models.binding.CommentBindingModel;
import org.softuni.secondtech.models.binding.CommentUpdateBindingModel;
import org.softuni.secondtech.services.CommentService;
import org.softuni.secondtech.services.JmsService;
import org.softuni.secondtech.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final JmsService jmsService;
    private final CommentService commentService;
    private final ModelService modelService;

    @Autowired
    public CommentController(JmsService jmsService, CommentService commentService, ModelService modelService) {
        this.jmsService = jmsService;
        this.commentService = commentService;
        this.modelService = modelService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postAdd(@RequestBody CommentBindingModel commentBindingModel, Principal principal) {
        if (principal != null) {
            commentBindingModel.setUsername(principal.getName());
            this.jmsService.sendComment(commentBindingModel);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/pending")
    public String getAll(Model model) {
        model.addAttribute("comments", this.modelService.mapCommentListToCommentPendingViewModelList(
                this.commentService.findAllPendingComments()));
        return "comments/pending";
    }

    @PostMapping(value = "/update", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postUpdate(@RequestBody CommentUpdateBindingModel commentUpdateBindingModel) {
        this.jmsService.sendComment(commentUpdateBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }
}
