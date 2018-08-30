package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean usernameExists(String username);

    boolean emailExists(String email);

    void saveUser(User user);

    void saveModerator(User user);

    User getUserByUsername(String username);

    Page<User> findAllExceptAdmin(Pageable pageable);

    void removeUserById(String id);
}
