package org.softuni.secondtechjmscomment.services;

import org.softuni.secondtechjmscomment.entities.Comment;
import org.softuni.secondtechjmscomment.models.binding.CommentUpdateBindingModel;

public interface CommentService {
    void saveComment(Comment comment);

    void updateComment(CommentUpdateBindingModel commentUpdateBindingModel);
}
