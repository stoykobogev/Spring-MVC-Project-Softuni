package org.softuni.secondtech.repository;

import org.softuni.secondtech.domain.entities.BaseProduct;
import org.softuni.secondtech.domain.entities.Comment;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByProduct(BaseProduct baseProduct);

    List<Comment> findAllByProductAndStatusNot(BaseProduct baseProduct, CommentStatus status);

    List<Comment> findAllByStatus(CommentStatus commentStatus);

    void deleteAllByUser(User user);
}
