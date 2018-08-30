package org.softuni.secondtech.repositories;

import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.entities.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select case when count(u) > 0 then true else false end from User u " +
            "where u.username = ?1")
    boolean usernameExists(String username);

    @Query("select case when count(u) > 0 then true else false end from User u " +
            "where u.email = ?1")
    boolean emailExists(String email);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    //@Query("select u from User as u join u.authorities as a where a not like :role group by u")
    Page<User> findAllByAuthoritiesNotContaining(UserRole userRole, Pageable pageable);
}
