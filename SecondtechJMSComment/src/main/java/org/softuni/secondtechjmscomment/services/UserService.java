package org.softuni.secondtechjmscomment.services;

import org.softuni.secondtechjmscomment.entities.User;

public interface UserService {

    User getUserByUsername(String username);
}
