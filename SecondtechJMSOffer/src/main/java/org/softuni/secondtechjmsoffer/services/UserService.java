package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.User;

public interface UserService {

    User getUserByUsername(String username);
}
