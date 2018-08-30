package org.softuni.secondtechjmscomment.repository;

import org.softuni.secondtechjmscomment.domain.entities.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {
}
