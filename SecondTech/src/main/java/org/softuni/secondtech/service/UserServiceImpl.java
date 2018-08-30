package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.*;
import org.softuni.secondtech.domain.enums.Roles;
import org.softuni.secondtech.repository.UserRepository;
import org.softuni.secondtech.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

            List<Laptop> laptopList = this.laptopService.findAllByUser(user);
            List<SmartPhone> smartPhoneList = this.smartphoneService.findAllByUser(user);
            List<Tablet> tabletList = this.tabletService.findAllByUser(user);

            if (laptopList != null && smartPhoneList != null && tabletList != null) {
                if (!user.extractRole().equals(Roles.MODERATOR.name())) {
                    this.cloudinaryService.deleteImages(laptopList);
                    this.laptopService.deleteAllByUser(user);

                    this.cloudinaryService.deleteImages(smartPhoneList);
                    this.smartphoneService.deleteAllByUser(user);

                    this.cloudinaryService.deleteImages(tabletList);
                    this.tabletService.deleteAllByUser(user);
                } else {
                    this.laptopService.removeUserFromLaptops(user, laptopList);
                    this.smartphoneService.removeUserFromSmartphones(user, smartPhoneList);
                    this.tabletService.removeUserFromTablets(user, tabletList);
                }
            }

            this.userRepository.delete(user);
        }
    }
}
