package org.softuni.secondtech.repository;

import org.softuni.secondtech.domain.entities.Laptop;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
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
