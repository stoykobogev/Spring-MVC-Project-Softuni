package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.*;
import org.softuni.secondtechjmsorder.domain.enums.ProductStatus;
import org.softuni.secondtechjmsorder.domain.enums.Roles;
import org.softuni.secondtechjmsorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final LaptopService laptopService;
    private final SmartphoneService smartphoneService;
    private final TabletService tabletService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, LaptopService laptopService, SmartphoneService smartphoneService, TabletService tabletService) {
        this.orderRepository = orderRepository;
        this.laptopService = laptopService;
        this.smartphoneService = smartphoneService;
        this.tabletService = tabletService;
    }

    @Override
    public void saveOrder(Order order, String productType) {
        Set<UserRole> userRoles = order.getUser().getAuthorities();
        if (userRoles.size() == 1
                && userRoles.iterator().next().getAuthority().equals(Roles.USER.name())) {
            BaseProduct product = order.getProduct();
            product.setStatus(ProductStatus.SOLD);

            if (productType.equals("laptop")) {
                this.laptopService.saveLaptop((Laptop) product);
            } else if (productType.equals("smartphone")) {
                this.smartphoneService.saveSmartphone((SmartPhone) product);
            } else if (productType.equals("tablet")) {
                this.tabletService.saveTablet((Tablet) product);
            }
        }

        this.orderRepository.saveAndFlush(order);
    }
}
