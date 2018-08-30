package org.softuni.secondtechjmscomment.repository;

import org.softuni.secondtechjmscomment.domain.entities.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {
}
