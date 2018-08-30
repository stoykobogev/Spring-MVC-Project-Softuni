package org.softuni.secondtechjmsorder.repositories;

import org.softuni.secondtechjmsorder.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
