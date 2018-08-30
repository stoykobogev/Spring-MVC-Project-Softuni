package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.BaseProduct;
import org.softuni.secondtech.domain.entities.Comment;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.CommentStatus;
import org.softuni.secondtech.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsByProductNotRejected(BaseProduct baseProduct) {
        return this.commentRepository.findAllByProductAndStatusNot(baseProduct, CommentStatus.REJECTED);
    }

    @Override
    public List<Comment> findAllPendingComments() {
        return this.commentRepository.findAllByStatus(CommentStatus.PENDING);
    }

    @Override
    @Transactional
    public void deleteAllByUser(User user) {
        this.commentRepository.deleteAllByUser(user);
    }
}
