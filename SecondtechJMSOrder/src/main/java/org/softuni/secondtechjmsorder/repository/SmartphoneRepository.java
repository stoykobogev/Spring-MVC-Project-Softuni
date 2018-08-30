package org.softuni.secondtechjmsorder.repository;

import org.softuni.secondtechjmsorder.domain.entities.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {
}
