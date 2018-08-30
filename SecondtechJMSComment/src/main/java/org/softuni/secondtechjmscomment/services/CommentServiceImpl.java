package org.softuni.secondtechjmscomment.services;

import org.softuni.secondtechjmscomment.entities.Comment;
import org.softuni.secondtechjmscomment.enums.CommentStatus;
import org.softuni.secondtechjmscomment.models.binding.CommentUpdateBindingModel;
import org.softuni.secondtechjmscomment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveComment(Comment comment) {
        this.commentRepository.saveAndFlush(comment);
    }

    @Override
    public void updateComment(CommentUpdateBindingModel commentUpdateBindingModel) {
        Comment comment = this.commentRepository.findById(commentUpdateBindingModel.getId()).orElse(null);

        if (comment != null) {
            comment.setStatus(CommentStatus.valueOf(commentUpdateBindingModel.getStatus()));
            this.commentRepository.saveAndFlush(comment);
        }
    }
}
