package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.Order;
import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return this.orderRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteAllByUser(User user) {
        this.orderRepository.deleteAllByUser(user);
    }
}
