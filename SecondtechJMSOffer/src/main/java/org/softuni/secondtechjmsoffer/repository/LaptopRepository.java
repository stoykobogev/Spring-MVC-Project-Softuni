package org.softuni.secondtechjmsoffer.repository;

import org.softuni.secondtechjmsoffer.domain.entities.Laptop;
import org.softuni.secondtechjmsoffer.domain.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {

    void deleteAllByStatus(ProductStatus productStatus);

    List<Laptop> findAllByStatus(ProductStatus productStatus);
}
