package org.softuni.secondtechjmscomment.repositories;

import org.softuni.secondtechjmscomment.entities.BaseProduct;
import org.softuni.secondtechjmscomment.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}
