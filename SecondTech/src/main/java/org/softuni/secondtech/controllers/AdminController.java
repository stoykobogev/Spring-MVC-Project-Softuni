package org.softuni.secondtech.controllers;

import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.models.binding.UserRegisterBindingModel;
import org.softuni.secondtech.services.ModelService;
import org.softuni.secondtech.services.UserService;
import org.softuni.secondtech.validators.UserRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ModelService modelService;
    private final UserRegisterValidator validator;

    @Autowired
    public AdminController(UserService userService, ModelService modelService, UserRegisterValidator validator) {
        this.userService = userService;
        this.modelService = modelService;
        this.validator = validator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }

    @GetMapping("/users")
    public String getUsers(Model model, @PageableDefault(size = 20) Pageable pageable) {
        Page<User> users = this.userService.findAllExceptAdmin(pageable);
        model.addAttribute("users", this.modelService.mapUserPageToUserAllViewModelPage(users));

        return "admin/users";
    }

    @PostMapping(value = "/users/remove")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity getAdd(@RequestParam String id) {
        this.userService.removeUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/register")
    public String registerGet(@ModelAttribute("model") UserRegisterBindingModel bindingModel) {
        return "admin/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("model") UserRegisterBindingModel bindingModel,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            return "admin/register";
        } else {
            this.userService.saveModerator(this.modelService.mapModelToModerator(bindingModel));
            return "users/home";
        }
    }
}
