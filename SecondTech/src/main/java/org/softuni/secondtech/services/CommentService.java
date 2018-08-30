package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.BaseProduct;
import org.softuni.secondtech.entities.Comment;
import org.softuni.secondtech.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByProductNotRejected(BaseProduct baseProduct);

    List<Comment> findAllPendingComments();

    @Transactional
    void deleteAllByUser(User user);
}
