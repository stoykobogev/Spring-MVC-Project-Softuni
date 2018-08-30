package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.User;

public interface UserService {

    User getUserByUsername(String username);
}
