package org.softuni.secondtech.web.validators;

import org.softuni.secondtech.web.exceptions.FormArgumentException;
import org.softuni.secondtech.domain.models.binding.UserRegisterBindingModel;
import org.softuni.secondtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegisterValidator implements Validator {

    private static final String USERNAME_EXISTS_MESSAGE = "Username already exists";
    private static final String EMAIL_EXISTS_MESSAGE = "Email already exists";
    private static final String PASSWORDS_MISMATCH_MESSAGE = "Confirm Password is different form Password";
    private static final String GENERIC_EMPTY_FIELD_MESSAGE = "Field cannot be empty";

    private final UserService userService;

    @Autowired
    public UserRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
//        return UserRegisterBindingModel.class.isAssignableFrom(clazz)
//                || ModeratorRegisterBindingModel.class.isAssignableFrom(clazz);
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterBindingModel model = (UserRegisterBindingModel) target;

        try {
            validateEmail(model, errors);
            validatePasswords(model, errors);
            validateUsername(model, errors);
        } catch (Exception e) {
            throw new FormArgumentException();
        }
    }

    private void validateEmail(UserRegisterBindingModel model, Errors errors) {
        String email = model.getEmail();
        if (email == null || email.isEmpty()) {
            errors.rejectValue("email", "EMPTY_EMAIL", GENERIC_EMPTY_FIELD_MESSAGE);
        } else if (this.userService.emailExists(model.getEmail())) {
            errors.rejectValue("email", "EMAIL_EXISTS", EMAIL_EXISTS_MESSAGE);
        }
    }

    private void validateUsername(UserRegisterBindingModel model, Errors errors) {
        String username = model.getUsername();
        if (username == null || username.trim().isEmpty()) {
            errors.rejectValue("username", "EMPTY_USERNAME", GENERIC_EMPTY_FIELD_MESSAGE);
        } else if (this.userService.usernameExists(username)) {
            errors.rejectValue("username", "USERNAME_EXISTS", USERNAME_EXISTS_MESSAGE);
        }
    }

    private void validatePasswords(UserRegisterBindingModel model, Errors errors) {
        String password = model.getPassword();
        if (password == null || password.isEmpty()) {
            errors.rejectValue("password", "EMPTY_PASSWORD", GENERIC_EMPTY_FIELD_MESSAGE);
        } else if (!password.equals(model.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "PASSWORD_MISMATCH", PASSWORDS_MISMATCH_MESSAGE);
        }
    }
}
