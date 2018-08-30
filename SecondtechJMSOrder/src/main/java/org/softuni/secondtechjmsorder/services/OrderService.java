package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.Order;

public interface OrderService {
    void saveOrder(Order order, String productType);
}
