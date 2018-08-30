package org.softuni.secondtechjmsorder.repositories;

import org.softuni.secondtechjmsorder.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {
}
