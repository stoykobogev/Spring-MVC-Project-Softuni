package org.softuni.secondtechjmsoffer.repositories;

import org.softuni.secondtechjmsoffer.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {

    void deleteAllByStatus(ProductStatus productStatus);

    List<SmartPhone> findAllByStatus(ProductStatus productStatus);
}
