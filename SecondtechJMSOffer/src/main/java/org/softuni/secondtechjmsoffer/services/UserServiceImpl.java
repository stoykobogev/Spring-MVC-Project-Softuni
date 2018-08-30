package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.User;
import org.softuni.secondtechjmsoffer.repositories.UserRepository;
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
