package org.softuni.secondtech.domain.models.view;

public class CommentDetailsViewModel {

    private String username;
    private String content;

    public CommentDetailsViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
