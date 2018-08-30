package org.softuni.secondtech.domain.models.view;

public class CommentPendingViewModel {

    private String id;
    private String username;
    private String content;

    public CommentPendingViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
