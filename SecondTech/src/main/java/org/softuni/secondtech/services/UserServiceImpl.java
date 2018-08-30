package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.*;
import org.softuni.secondtech.enums.Roles;
import org.softuni.secondtech.repositories.UserRepository;
import org.softuni.secondtech.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OrderService orderService;
    private final CommentService commentService;
    private final LaptopService laptopService;
    private final SmartphoneService smartphoneService;
    private final TabletService tabletService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, OrderService orderService,
                           CommentService commentService, @Lazy LaptopService laptopService,
                           @Lazy SmartphoneService smartphoneService, @Lazy TabletService tabletService,
                           CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.orderService = orderService;
        this.commentService = commentService;
        this.laptopService = laptopService;
        this.smartphoneService = smartphoneService;
        this.tabletService = tabletService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public boolean usernameExists(String username) {
        return this.userRepository.usernameExists(username);
    }

    @Override
    public boolean emailExists(String email) {
        return this.userRepository.emailExists(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> authorities = this.userRoleRepository.findByAuthority("USER");
        user.setAuthorities(authorities);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void saveModerator(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> authorities = this.userRoleRepository.findAllByAuthorityIn("USER", "MODERATOR");
        user.setAuthorities(authorities);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public Page<User> findAllExceptAdmin(Pageable pageable) {
        Set<UserRole> roles = this.userRoleRepository.findByAuthority(Roles.ADMIN.name());
        return this.userRepository.findAllByAuthoritiesNotContaining(roles.iterator().next(), pageable);
    }

    @Override
    public void removeUserById(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.orderService.deleteAllByUser(user);
            this.commentService.deleteAllByUser(user);
            if (!user.extractRole().equals(Roles.MODERATOR.name())) {
                for (Laptop laptop : this.laptopService.findAllByUser(user)) {
                    this.cloudinaryService.deleteImage(laptop);
                }
                this.laptopService.deleteAllByUser(user);

                for (SmartPhone smartPhone : this.smartphoneService.findAllByUser(user)) {
                    this.cloudinaryService.deleteImage(smartPhone);
                }
                this.smartphoneService.deleteAllByUser(user);

                for (Tablet tablet : this.tabletService.findAllByUser(user)) {
                    this.cloudinaryService.deleteImage(tablet);
                }
                this.tabletService.deleteAllByUser(user);
            } else {
                for (Laptop laptop : this.laptopService.findAllByUser(user)) {
                    laptop.setUser(null);
                    this.laptopService.saveLaptop(laptop);
                }
                for (SmartPhone smartPhone : this.smartphoneService.findAllByUser(user)) {
                    smartPhone.setUser(null);
                    this.smartphoneService.saveSmartphone(smartPhone);
                }
                for (Tablet tablet : this.tabletService.findAllByUser(user)) {
                    tablet.setUser(null);
                    this.tabletService.saveTablet(tablet);
                }
            }
            this.userRepository.delete(user);
        }
    }
}
