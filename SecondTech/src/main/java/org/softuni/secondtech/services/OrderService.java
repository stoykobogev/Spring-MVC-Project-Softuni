package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.Order;
import org.softuni.secondtech.entities.User;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();

    List<Order> findAllByUser(User user);

    void deleteAllByUser(User user);
}
