package org.softuni.secondtechjmsorder.repository;

import org.softuni.secondtechjmsorder.domain.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {
}
