package org.softuni.secondtech.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.secondtech.domain.entities.Order;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.repository.OrderRepository;
import org.softuni.secondtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void init() {
        this.user = new User();
        this.user.setEmail("email");
        this.user.setPassword("password");
        this.user.setUsername("username");
        this.user = this.userRepository.saveAndFlush(this.user);

        Order order = new Order();
        order.setUser(this.user);
        this.orderRepository.save(order);

        Order order2 = new Order();
        order2.setUser(this.user);
        this.orderRepository.save(order2);

        Order order3 = new Order();
        this.orderRepository.save(order3);

        this.orderRepository.flush();
    }

    @Test
    public void findAllOrders_returnsThreeOrders() {
        List<Order> result = this.orderService.findAllOrders();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    public void findAllByUser_returnsTwoOrders() {
        List<Order> resultList = this.orderService.findAllByUser(this.user);

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    public void deleteAllByUser_deletesTwoOrders() {
        this.orderService.deleteAllByUser(this.user);

        List<Order> resultList = this.orderRepository.findAll();

        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }
}
