package org.softuni.secondtechjmsoffer.repositories;

import org.softuni.secondtechjmsoffer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String username);
}
