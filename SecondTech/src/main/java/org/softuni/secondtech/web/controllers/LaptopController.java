package org.softuni.secondtech.web.controllers;

import org.softuni.secondtech.domain.entities.Laptop;
import org.softuni.secondtech.domain.models.binding.LaptopOfferBindingModel;
import org.softuni.secondtech.domain.models.binding.LaptopUpdateBindingModel;
import org.softuni.secondtech.domain.models.view.CommentDetailsViewModel;
import org.softuni.secondtech.service.CommentService;
import org.softuni.secondtech.service.JmsService;
import org.softuni.secondtech.service.LaptopService;
import org.softuni.secondtech.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/laptops")
public class LaptopController {

    private final LaptopService laptopService;
    private final ModelService modelService;
    private final CommentService commentService;
    private final JmsService jmsService;

    @Autowired
    public LaptopController(LaptopService laptopService, ModelService modelService,
                            CommentService commentService, JmsService jmsService) {
        this.laptopService = laptopService;
        this.modelService = modelService;
        this.commentService = commentService;
        this.jmsService = jmsService;
    }

    @GetMapping("/all")
    public String getAll(Model model, @PageableDefault(size = 20) Pageable pageable) {
        Page<Laptop> laptopsPage = this.laptopService.getPageWithApproved(pageable);
        model.addAttribute("laptopsPage",
                this.modelService.mapLaptopPageToLaptopsAllViewModelPage(laptopsPage));
        return "laptops/all";
    }

    @GetMapping("/details/{id}")
    public String getDetails(Model model, @PathVariable String id) {
        Laptop laptop = this.laptopService.findApprovedById(id);
        if (laptop != null) {
            model.addAttribute("laptop", this.modelService.mapLaptopToLaptopDetailsViewModel(laptop));
            List<CommentDetailsViewModel> comments = this.modelService.mapCommentListToCommentDetailsViewModelList(
                    this.commentService.getCommentsByProductNotRejected(laptop));
            model.addAttribute("comments", comments);
        }

        return "laptops/details";
    }

    @GetMapping("/offer")
    public String getOffer(@ModelAttribute("model") LaptopOfferBindingModel laptopOfferBindingModel) {
        return "laptops/offer";
    }

    @PostMapping("/offer")
    public String postOffer(@Valid @ModelAttribute LaptopOfferBindingModel laptopOfferBindingModel,
                            Principal principal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            return "laptops/offer";
        } else {
            this.jmsService.sendLaptopOffer(laptopOfferBindingModel, principal);
            return "users/home";
        }
    }

    @GetMapping("/pending")
    public String getPending(Model model) {
        model.addAttribute("laptops", this.modelService.mapLaptopListToLaptopDetailsViewModelList(
                this.laptopService.findAllPending()));
        return "laptops/pending";
    }

    @PostMapping(value = "/update", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postAdd(@RequestBody LaptopUpdateBindingModel laptopUpdateBindingModel) {
        this.jmsService.sendLaptopOfferUpdate(laptopUpdateBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/mine")
    public String getMine(Principal principal, Model model) {
        List<Laptop> laptopList = this.laptopService.findAllByUsername(principal.getName());
        if (laptopList != null) {
            model.addAttribute("laptops",
                    this.modelService.mapLaptopListToLaptopMineViewModelList(laptopList));
        }
        return "laptops/mine";
    }

    @PostMapping(value = "/remove")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postRemove(@RequestParam String id, Principal principal) {
        this.jmsService.sendLaptopOfferRemove(id, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/remove")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getRemove(Model model) {
        List<Laptop> laptopList = this.laptopService.findAllApproved();
        if (laptopList != null) {
            model.addAttribute("laptops",
                    this.modelService.mapLaptopListToLaptopRemoveViewModelList(laptopList));
        }
        return "laptops/remove";
    }
}
