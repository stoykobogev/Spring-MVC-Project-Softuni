package org.softuni.secondtech.repository;

import org.softuni.secondtech.domain.entities.Order;
import org.softuni.secondtech.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByUser(User user);

    void deleteAllByUser(User user);
}
