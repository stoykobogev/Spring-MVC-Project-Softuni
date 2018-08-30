package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.User;
import org.softuni.secondtechjmsorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }
}
