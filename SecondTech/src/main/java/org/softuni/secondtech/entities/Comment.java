package org.softuni.secondtech.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.secondtech.enums.CommentStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "comment_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private BaseProduct product;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    public Comment() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BaseProduct getProduct() {
        return this.product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public CommentStatus getStatus() {
        return this.status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}
