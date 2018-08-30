package org.softuni.secondtech.controllers;

import org.softuni.secondtech.models.binding.UserRegisterBindingModel;
import org.softuni.secondtech.services.ModelService;
import org.softuni.secondtech.services.UserService;
import org.softuni.secondtech.validators.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRegisterValidator validator;
    private final ModelService modelService;
    private final UserService userService;

    @Autowired
    public UserController(UserRegisterValidator validator, ModelService modelService, UserService userService) {
        this.validator = validator;
        this.modelService = modelService;
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String loginGet() {
        return "users/login";
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerGet(@ModelAttribute("model") UserRegisterBindingModel bindingModel) {
        return "users/register";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerPost(@Valid @ModelAttribute("model") UserRegisterBindingModel bindingModel,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            return "users/register";
        } else {
            this.userService.saveUser(this.modelService.mapModelToUser(bindingModel));
            return "users/login";
        }
    }
}
