package org.softuni.secondtechjmscomment.listeners;

import org.softuni.secondtechjmscomment.entities.Comment;
import org.softuni.secondtechjmscomment.models.binding.CommentBindingModel;
import org.softuni.secondtechjmscomment.models.binding.CommentUpdateBindingModel;
import org.softuni.secondtechjmscomment.services.CommentService;
import org.softuni.secondtechjmscomment.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CommentListener {

    private final ModelService modelService;
    private final CommentService commentService;

    @Autowired
    public CommentListener(ModelService modelService, CommentService commentService) {
        this.modelService = modelService;
        this.commentService = commentService;
    }

    @JmsListener(destination = "comments", containerFactory = "myFactory")
    public void receiveComment(CommentBindingModel commentBindingModel) {
        Comment comment = this.modelService.mapCommentBindingModelToComment(commentBindingModel);
        if (comment != null) {
            this.commentService.saveComment(comment);
        }
    }

    @JmsListener(destination = "comments-update", containerFactory = "myFactory")
    public void receiveComment(CommentUpdateBindingModel commentUpdateBindingModel) {
        this.commentService.updateComment(commentUpdateBindingModel);
    }
}
