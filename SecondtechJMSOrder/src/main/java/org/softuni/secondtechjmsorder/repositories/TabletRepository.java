package org.softuni.secondtechjmsorder.repositories;

import org.softuni.secondtechjmsorder.entities.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {
}
