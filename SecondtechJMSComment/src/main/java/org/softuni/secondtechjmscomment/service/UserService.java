package org.softuni.secondtechjmscomment.service;

import org.softuni.secondtechjmscomment.domain.entities.User;

public interface UserService {

    User getUserByUsername(String username);
}
