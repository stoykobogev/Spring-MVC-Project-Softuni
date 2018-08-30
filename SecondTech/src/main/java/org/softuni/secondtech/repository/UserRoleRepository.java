package org.softuni.secondtech.repository;

import org.softuni.secondtech.domain.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    Set<UserRole> findByAuthority(String authority);

    Set<UserRole> findAllByAuthorityIn(String... authority);
}
