package org.softuni.secondtech.repositories;

import org.softuni.secondtech.entities.Order;
import org.softuni.secondtech.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByUser(User user);

    void deleteAllByUser(User user);
}
