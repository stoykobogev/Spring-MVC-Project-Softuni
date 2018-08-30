package org.softuni.secondtechjmsorder.listeners;

import org.softuni.secondtechjmsorder.entities.Order;
import org.softuni.secondtechjmsorder.models.binding.OrderBindingModel;
import org.softuni.secondtechjmsorder.services.ModelService;
import org.softuni.secondtechjmsorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final ModelService modelService;
    private final OrderService orderService;

    @Autowired
    public OrderListener(ModelService modelService, OrderService orderService) {
        this.modelService = modelService;
        this.orderService = orderService;
    }

    @JmsListener(destination = "orders", containerFactory = "myFactory")
    public void receiveOrder(OrderBindingModel orderBindingModel) {
        Order order = this.modelService.mapOrderBindingModelToOrder(orderBindingModel);
        if (order != null) {
            this.orderService.saveOrder(order, orderBindingModel.getProductType());
        }
    }
}
