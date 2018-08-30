package org.softuni.secondtech.controllers;

import org.softuni.secondtech.entities.Tablet;
import org.softuni.secondtech.models.binding.TabletOfferBindingModel;
import org.softuni.secondtech.models.binding.TabletUpdateBindingModel;
import org.softuni.secondtech.models.view.CommentDetailsViewModel;
import org.softuni.secondtech.services.CommentService;
import org.softuni.secondtech.services.JmsService;
import org.softuni.secondtech.services.ModelService;
import org.softuni.secondtech.services.TabletService;
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
@RequestMapping("/tablets")
public class TabletController {

    private final TabletService tabletService;
    private final ModelService modelService;
    private final CommentService commentService;
    private final JmsService jmsService;

    @Autowired
    public TabletController(TabletService tabletService, ModelService modelService,
                            CommentService commentService, JmsService jmsService) {
        this.tabletService = tabletService;
        this.modelService = modelService;
        this.commentService = commentService;
        this.jmsService = jmsService;
    }

    @GetMapping("/all")
    public String getAll(Model model, @PageableDefault(size = 20) Pageable pageable) {
        Page<Tablet> tabletsPage = this.tabletService.getPageWithApproved(pageable);
        model.addAttribute("tabletsPage",
                this.modelService.mapTabletPageToTabletsAllViewModelPage(tabletsPage));
        return "tablets/all";
    }

    @GetMapping("/details/{id}")
    public String getDetails(Model model, @PathVariable String id) {
        Tablet tablet = this.tabletService.findApprovedById(id);
        if (tablet != null) {
            model.addAttribute("tablet", this.modelService.mapTabletToTabletDetailsViewModel(tablet));
            List<CommentDetailsViewModel> comments = this.modelService.mapCommentListToCommentDetailsViewModelList(
                    this.commentService.getCommentsByProductNotRejected(tablet));
            model.addAttribute("comments", comments);
        }

        return "tablets/details";
    }

    @GetMapping("/offer")
    public String getOffer(@ModelAttribute("model") TabletOfferBindingModel tabletOfferBindingModel) {
        return "tablets/offer";
    }

    @PostMapping("/offer")
    public String postOffer(@Valid @ModelAttribute TabletOfferBindingModel tabletOfferBindingModel,
                            Principal principal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            return "tablets/offer";
        } else {
            this.jmsService.sendTabletOffer(tabletOfferBindingModel, principal);
            return "users/home";
        }
    }

    @GetMapping("/pending")
    public String getPending(Model model) {
        model.addAttribute("tablets", this.modelService.mapTabletListToTabletDetailsViewModelList(
                this.tabletService.findAllPending()));
        return "tablets/pending";
    }

    @PostMapping(value = "/update", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postAdd(@RequestBody TabletUpdateBindingModel tabletUpdateBindingModel) {
        this.jmsService.sendTabletOfferUpdate(tabletUpdateBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/mine")
    public String getMine(Principal principal, Model model) {
        List<Tablet> tabletList = this.tabletService.findAllByUsername(principal.getName());
        if (tabletList != null) {
            model.addAttribute("tablets",
                    this.modelService.mapTabletListToTabletMineViewModelList(tabletList));
        }
        return "tablets/mine";
    }

    @PostMapping(value = "/remove")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity postRemove(@RequestParam String id, Principal principal) {
        this.jmsService.sendTabletOfferRemove(id, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/remove")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getRemove(Model model) {
        List<Tablet> tabletList = this.tabletService.findAllApproved();
        if (tabletList != null) {
            model.addAttribute("tablets",
                    this.modelService.mapTabletListToTabletRemoveViewModelList(tabletList));
        }
        return "tablets/remove";
    }
}
