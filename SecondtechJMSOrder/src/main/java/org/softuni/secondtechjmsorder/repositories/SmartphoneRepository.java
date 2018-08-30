package org.softuni.secondtechjmsorder.repositories;

import org.softuni.secondtechjmsorder.entities.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {
}
