package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.User;

public interface UserService {

    User getUserByUsername(String username);
}
