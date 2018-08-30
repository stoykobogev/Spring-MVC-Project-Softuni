package org.softuni.secondtechjmsoffer.repositories;

import org.softuni.secondtechjmsoffer.entities.Tablet;
import org.softuni.secondtechjmsoffer.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {

    void deleteAllByStatus(ProductStatus productStatus);

    List<Tablet> findAllByStatus(ProductStatus productStatus);
}
