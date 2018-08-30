package org.softuni.secondtechjmscomment.repositories;

import org.softuni.secondtechjmscomment.entities.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabletRepository extends JpaRepository<Tablet, String> {
}
