package org.softuni.secondtechjmscomment.repositories;

import org.softuni.secondtechjmscomment.entities.SmartPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends JpaRepository<SmartPhone, String> {
}
