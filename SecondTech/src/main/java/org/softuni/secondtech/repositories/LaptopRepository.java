package org.softuni.secondtech.repositories;

import org.softuni.secondtech.entities.Laptop;
import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {

    List<Laptop> findAllByStatus(ProductStatus status);

    Page<Laptop> findAllByStatus(ProductStatus status, Pageable pageable);

    Laptop findByIdAndStatus(String id, ProductStatus status);

    List<Laptop> findAllByUser(User user);

    void deleteAllByUser(User user);
}
