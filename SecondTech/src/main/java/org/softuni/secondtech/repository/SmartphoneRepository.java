package org.softuni.secondtech.repository;

import org.softuni.secondtech.domain.entities.SmartPhone;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {

    List<SmartPhone> findAllByStatus(ProductStatus status);

    Page<SmartPhone> findAllByStatus(ProductStatus status, Pageable pageable);

    SmartPhone findByIdAndStatus(String id, ProductStatus status);

    List<SmartPhone> findAllByUser(User user);

    void deleteAllByUser(User user);
}
