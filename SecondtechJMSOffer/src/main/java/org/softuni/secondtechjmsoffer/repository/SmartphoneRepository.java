package org.softuni.secondtechjmsoffer.repository;

import org.softuni.secondtechjmsoffer.domain.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.domain.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {

    void deleteAllByStatus(ProductStatus productStatus);

    List<SmartPhone> findAllByStatus(ProductStatus productStatus);
}
