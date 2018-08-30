package org.softuni.secondtech.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.secondtech.entities.Comment;
import org.softuni.secondtech.entities.Laptop;
import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.enums.CommentStatus;
import org.softuni.secondtech.repositories.CommentRepository;
import org.softuni.secondtech.repositories.LaptopRepository;
import org.softuni.secondtech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CommentServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    private Laptop laptop;

    @Before
    public void init() {
        this.user = new User();
        this.user.setEmail("email");
        this.user.setPassword("password");
        this.user.setUsername("username");
        this.user = this.userRepository.saveAndFlush(this.user);
        this.laptop = new Laptop();
        this.laptop.setPrice(BigDecimal.ONE);
        this.laptop = this.laptopRepository.saveAndFlush(laptop);
        Comment pendingComment = new Comment();
        pendingComment.setStatus(CommentStatus.PENDING);
        pendingComment.setProduct(this.laptop);
        pendingComment.setUser(this.user);
        Comment rejectedComment = new Comment();
        rejectedComment.setStatus(CommentStatus.REJECTED);
        rejectedComment.setProduct(this.laptop);
        rejectedComment.setUser(this.user);
        Comment approvedComment = new Comment();
        approvedComment.setStatus(CommentStatus.APPROVED);
        approvedComment.setProduct(this.laptop);

        this.commentRepository.save(pendingComment);
        this.commentRepository.save(rejectedComment);
        this.commentRepository.save(approvedComment);
    }

    @Test
    public void getCommentsByProductNotRejected_mixedComments_returnsNotRejectedComments() {
        List<Comment> commentList = this.commentService.getCommentsByProductNotRejected(this.laptop);

        assertEquals(2, commentList.size());
        for(Comment comment : commentList) {
            assertNotEquals(CommentStatus.REJECTED, comment.getStatus());
        }
    }

    @Test
    public void findAllPendingComments_mixedComments_returnsPendingComments() {
        List<Comment> commentList = this.commentService.findAllPendingComments();

        assertEquals(1, commentList.size());
        for (Comment comment : commentList) {
            assertEquals(CommentStatus.PENDING, comment.getStatus());
        }
    }

    @Test
    public void deleteAllByUser_oneCommentInRepository() {
        this.commentService.deleteAllByUser(this.user);
        List<Comment> commentList = this.commentRepository.findAll();

        assertEquals(1, commentList.size());
    }
}
