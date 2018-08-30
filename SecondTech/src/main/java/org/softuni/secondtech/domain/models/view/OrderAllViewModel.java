package org.softuni.secondtech.domain.models.view;

public class OrderAllViewModel {

    private String username;
    private String email;
    private String orderedOn;
    private String productName;
    private String productHref;

    public OrderAllViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderedOn() {
        return this.orderedOn;
    }

    public void setOrderedOn(String orderedOn) {
        this.orderedOn = orderedOn;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductHref() {
        return this.productHref;
    }

    public void setProductHref(String productHref) {
        this.productHref = productHref;
    }
}
