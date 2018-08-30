package org.softuni.secondtech.web.controllers;

import org.softuni.secondtech.domain.models.binding.OrderBindingModel;
import org.softuni.secondtech.service.JmsService;
import org.softuni.secondtech.service.ModelService;
import org.softuni.secondtech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final JmsService jmsService;
    private final OrderService orderService;
    private final ModelService modelService;

    @Autowired
    public OrderController(JmsService jmsService, OrderService orderService, ModelService modelService) {
        this.jmsService = jmsService;
        this.orderService = orderService;
        this.modelService = modelService;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public ResponseEntity getAdd(@RequestBody OrderBindingModel orderBindingModel, Principal principal) {
        if (principal != null) {
            orderBindingModel.setOrderedOn(LocalDateTime.now());
            orderBindingModel.setUsername(principal.getName());
            this.jmsService.sendOrder(orderBindingModel);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("orders", this.modelService.mapOrderListToOrderAllViewModelList(
                this.orderService.findAllOrders()));
        return "orders/all";
    }
}
