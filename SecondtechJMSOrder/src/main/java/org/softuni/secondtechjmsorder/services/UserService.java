package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.User;

public interface UserService {

    User getUserByUsername(String username);
}
