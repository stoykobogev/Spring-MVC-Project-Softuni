package org.softuni.secondtechjmscomment.service;

import org.softuni.secondtechjmscomment.domain.entities.Comment;
import org.softuni.secondtechjmscomment.domain.models.binding.CommentUpdateBindingModel;

public interface CommentService {
    void saveComment(Comment comment);

    void updateComment(CommentUpdateBindingModel commentUpdateBindingModel);
}
