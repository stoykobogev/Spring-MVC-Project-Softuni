package org.softuni.secondtech.repository;

import org.softuni.secondtech.domain.entities.Tablet;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {

    List<Tablet> findAllByStatus(ProductStatus status);

    Page<Tablet> findAllByStatus(ProductStatus status, Pageable pageable);

    Tablet findByIdAndStatus(String id, ProductStatus status);

    List<Tablet> findAllByUser(User user);

    void deleteAllByUser(User user);
}
