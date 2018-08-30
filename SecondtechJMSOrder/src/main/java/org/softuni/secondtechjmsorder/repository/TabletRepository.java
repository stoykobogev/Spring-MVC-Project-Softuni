package org.softuni.secondtechjmsorder.repository;

import org.softuni.secondtechjmsorder.domain.entities.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {
}
