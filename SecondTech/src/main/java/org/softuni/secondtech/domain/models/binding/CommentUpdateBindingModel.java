package org.softuni.secondtech.domain.models.binding;

public class CommentUpdateBindingModel {

    private String id;
    private String status;

    public CommentUpdateBindingModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
