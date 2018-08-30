package org.softuni.secondtechjmsorder.repository;

import org.softuni.secondtechjmsorder.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
