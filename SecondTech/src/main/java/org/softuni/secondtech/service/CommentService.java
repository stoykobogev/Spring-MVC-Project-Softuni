package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.BaseProduct;
import org.softuni.secondtech.domain.entities.Comment;
import org.softuni.secondtech.domain.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByProductNotRejected(BaseProduct baseProduct);

    List<Comment> findAllPendingComments();

    @Transactional
    void deleteAllByUser(User user);
}
