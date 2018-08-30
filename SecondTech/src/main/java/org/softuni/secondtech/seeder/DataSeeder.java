package org.softuni.secondtech.seeder;

import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.enums.Roles;
import org.softuni.secondtech.repositories.UserRepository;
import org.softuni.secondtech.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DataSeeder(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (this.userRepository.findAll().isEmpty()) {
            User admin = createUser("admin", "admin@admin.com", "admin",
                    Roles.USER.name(), Roles.ADMIN.name(), Roles.MODERATOR.name());
            User moderator = createUser("moderator", "moderator@moderator.com", "moderator",
                    Roles.USER.name(), Roles.MODERATOR.name());
            User user = createUser("user", "user@user.com", "user", Roles.USER.name());
            User user2 = createUser("user2", "user2@user.com", "user2", Roles.USER.name());
            User user3 = createUser("user3", "user3@user.com", "user3", Roles.USER.name());
            User user4 = createUser("user4", "user4@user.com", "user4", Roles.USER.name());

            this.userRepository.save(admin);
            this.userRepository.save(moderator);
            this.userRepository.save(user);
            this.userRepository.save(user2);
            this.userRepository.save(user3);
            this.userRepository.save(user4);
            this.userRepository.flush();
        }
    }

    private User createUser(String username, String email, String password, String... authorities) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        user.setAuthorities(this.userRoleRepository.findAllByAuthorityIn(authorities));

        return user;
    }
}
