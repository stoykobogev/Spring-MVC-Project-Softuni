package org.softuni.secondtech.repositories;

import org.softuni.secondtech.entities.Tablet;
import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.enums.ProductStatus;
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
