package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.BaseProduct;
import org.softuni.secondtechjmsorder.domain.entities.Order;
import org.softuni.secondtechjmsorder.domain.entities.User;
import org.softuni.secondtechjmsorder.domain.models.binding.OrderBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final LaptopService laptopService;
    private final SmartphoneService smartphoneService;
    private final TabletService tabletService;
    private final UserService userService;

    @Autowired
    public ModelServiceImpl(LaptopService laptopService, SmartphoneService smartphoneService, TabletService tabletService, UserService userService) {
        this.laptopService = laptopService;
        this.smartphoneService = smartphoneService;
        this.tabletService = tabletService;
        this.userService = userService;
    }

    @Override
    public Order mapOrderBindingModelToOrder(OrderBindingModel orderBindingModel) {
        Order order = new Order();
        User user = this.userService.getUserByUsername(orderBindingModel.getUsername());
        BaseProduct product = getProduct(orderBindingModel.getProductType(), orderBindingModel.getProductId());
        if (user == null || product == null) {
            return null;
        }
        order.setUser(user);
        order.setProduct(product);
        order.setOrderedOn(orderBindingModel.getOrderedOn());

        return order;
    }

    private BaseProduct getProduct(String productType, String id) {
        if (productType.equals("laptop")) {
            return this.laptopService.findById(id);
        } else if (productType.equals("smartphone")) {
            return this.smartphoneService.findById(id);
        } else if (productType.equals("tablet")) {
            return this.tabletService.findById(id);
        } else {
            return null;
        }
    }
}
