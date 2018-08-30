package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.Laptop;
import org.softuni.secondtech.entities.User;
import org.softuni.secondtech.enums.ProductStatus;
import org.softuni.secondtech.enums.Roles;
import org.softuni.secondtech.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final UserService userService;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, UserService userService) {
        this.laptopRepository = laptopRepository;
        this.userService = userService;
    }

    @Override
    public List<Laptop> findAllApproved() {
        return this.laptopRepository.findAllByStatus(ProductStatus.APPROVED);
    }

    @Override
    public Page<Laptop> getPageWithApproved(Pageable pageable) {
        return this.laptopRepository.findAllByStatus(ProductStatus.APPROVED, pageable);
    }

    @Override
    public Laptop findApprovedById(String id) {
        return this.laptopRepository.findByIdAndStatus(id, ProductStatus.APPROVED);
    }

    @Override
    public List<Laptop> findAllPending() {
        return this.laptopRepository.findAllByStatus(ProductStatus.PENDING);
    }

    @Override
    public List<Laptop> findAllByUsername(String username) {
        User user = this.userService.getUserByUsername(username);
        if (user != null) {
            return this.laptopRepository.findAllByUser(user);
        }

        return null;
    }

    @Override
    public List<Laptop> findAllByUser(User user) {
        return this.laptopRepository.findAllByUser(user);
    }

    @Override
    public void saveLaptop(Laptop laptop) {
        this.laptopRepository.saveAndFlush(laptop);
    }

    @Override
    @Transactional
    public void deleteAllByUser(User user) {
        this.laptopRepository.deleteAllByUser(user);
    }
}
