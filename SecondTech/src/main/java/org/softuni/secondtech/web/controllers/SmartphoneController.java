package org.softuni.secondtech.web.controllers;

import org.softuni.secondtech.domain.entities.SmartPhone;
import org.softuni.secondtech.domain.models.binding.SmartphoneOfferBindingModel;
import org.softuni.secondtech.domain.models.binding.SmartphoneUpdateBindingModel;
import org.softuni.secondtech.domain.models.view.CommentDetailsViewModel;
import org.softuni.secondtech.service.CommentService;
import org.softuni.secondtech.service.JmsService;
import org.softuni.secondtech.service.ModelService;
import org.softuni.secondtech.service.SmartphoneService;
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
@RequestMapping("/smartphones")
public class SmartphoneController {

    private final SmartphoneService smartphoneService;
    private final ModelService modelService;
    private final CommentService commentService;
    private final JmsService jmsService;

    @Autowired
    public SmartphoneController(SmartphoneService smartphoneService, ModelService modelService,
                            CommentService commentService, JmsService jmsService) {
        this.smartphoneService = smartphoneService;
        this.modelService = modelService;
        this.commentService = commentService;
        this.jmsService = jmsService;
    }

    @GetMapping("/all")
    public String getAll(Model model, @PageableDefault(size = 20) Pageable pageable) {
        Page<SmartPhone> eventPage = this.smartphoneService.getPageWithApproved(pageable);
        model.addAttribute("smartphonesPage",
                this.modelService.mapSmartphonePageToSmartphonesAllViewModelPage(eventPage));
        return "smartphones/all";
    }

    @GetMapping("/details/{id}")
    public String getDetails(Model model, @PathVariable String id) {
        SmartPhone smartphone = this.smartphoneService.findApprovedById(id);
        if (smartphone != null) {
            model.addAttribute("smartphone",
                    this.modelService.mapSmartphoneToSmartphoneDetailsViewModel(smartphone));
            List<CommentDetailsViewModel> comments = this.modelService.mapCommentListToCommentDetailsViewModelList(
                    this.commentService.getCommentsByProductNotRejected(smartphone));
            model.addAttribute("comments", comments);
        }

        return "smartphones/details";
    }

    @GetMapping("/offer")
    public String getOffer(@ModelAttribute("model") SmartphoneOfferBindingModel smartphoneOfferBindingModel) {
        return "smartphones/offer";
    }

    @PostMapping("/offer")
    public String postOffer(@Valid @ModelAttribute SmartphoneOfferBindingModel smartphoneOfferBindingModel,
                            Principal principal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            return "smartphones/offer";
        } else {
            this.jmsService.sendSmartphoneOffer(smartphoneOfferBindingModel, principal);
            return "users/home";
        }
    }

    @GetMapping("/pending")
    public String getPending(Model model) {
        model.addAttribute("smartphones", this.modelService.mapSmartphoneListToSmartphoneDetailsViewModelList(
                this.smartphoneService.findAllPending()));
        return "smartphones/pending";
    }

    @PostMapping(value = "/update", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postAdd(@RequestBody SmartphoneUpdateBindingModel smartphoneUpdateBindingModel) {
        this.jmsService.sendSmartphoneOfferUpdate(smartphoneUpdateBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/mine")
    public String getMine(Principal principal, Model model) {
        List<SmartPhone> smartphoneList = this.smartphoneService.findAllByUsername(principal.getName());
        if (smartphoneList != null) {
            model.addAttribute("smartphones",
                    this.modelService.mapSmartphoneListToSmartphoneMineViewModelList(smartphoneList));
        }
        return "smartphones/mine";
    }

    @PostMapping(value = "/remove")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postRemove(@RequestParam String id, Principal principal) {
        this.jmsService.sendSmartphoneOfferRemove(id, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/remove")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getRemove(Model model) {
        List<SmartPhone> smartphoneList = this.smartphoneService.findAllApproved();
        if (smartphoneList != null) {
            model.addAttribute("smartphones",
                    this.modelService.mapSmartphoneListToSmartphoneRemoveViewModelList(smartphoneList));
        }
        return "smartphones/remove";
    }
}
