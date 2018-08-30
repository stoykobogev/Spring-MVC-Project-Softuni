package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.Order;

public interface OrderService {
    void saveOrder(Order order, String productType);
}
