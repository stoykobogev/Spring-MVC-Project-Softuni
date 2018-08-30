package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.Order;
import org.softuni.secondtech.domain.entities.User;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();

    List<Order> findAllByUser(User user);

    void deleteAllByUser(User user);
}
