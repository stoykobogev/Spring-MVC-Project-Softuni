package org.softuni.secondtech.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegisterBindingModel {

    @NotEmpty
    private String username;
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmPassword;

    public UserRegisterBindingModel() {
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
