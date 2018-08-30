package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.Laptop;
import org.softuni.secondtechjmsoffer.domain.entities.User;
import org.softuni.secondtechjmsoffer.domain.entities.UserRole;
import org.softuni.secondtechjmsoffer.domain.enums.ProductStatus;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopUpdateBindingModel;
import org.softuni.secondtechjmsoffer.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, CloudinaryService cloudinaryService, UserService userService) {
        this.laptopRepository = laptopRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @Override
    public void saveLaptop(Laptop laptop) {
        this.cloudinaryService.saveImage(laptop);
        this.laptopRepository.saveAndFlush(laptop);
    }

    @Override
    public void updateLaptop(LaptopUpdateBindingModel laptopUpdateBindingModel) {
        Laptop laptop = this.laptopRepository.findById(laptopUpdateBindingModel.getId()).orElse(null);
        if (laptop != null) {
            laptop.setStatus(ProductStatus.valueOf(laptopUpdateBindingModel.getStatus()));
            this.laptopRepository.saveAndFlush(laptop);
        }
    }

    @Override
    public void removeLaptop(LaptopRemoveBindingModel laptopRemoveBindingModel) {
        User user = this.userService.getUserByUsername(laptopRemoveBindingModel.getUsername());
        Laptop laptop = this.laptopRepository.findById(laptopRemoveBindingModel.getId()).orElse(null);

        if (checkAuthority(user, laptop)) {
            this.cloudinaryService.deleteImage(laptop);
            this.laptopRepository.delete(laptop);
        }
    }

    @Override
    public void deleteAllRejected() {
        this.laptopRepository.deleteAllByStatus(ProductStatus.REJECTED);
    }

    @Override
    public List<Laptop> findAllRejected() {
        return this.laptopRepository.findAllByStatus(ProductStatus.REJECTED);
    }

    private boolean checkAuthority(User user, Laptop laptop) {

        if (user == null || laptop == null) {
            return false;
        }

        for (UserRole role : user.getAuthorities()) {
            if (role.getAuthority().equals("MODERATOR")) {
                return true;
            }
        }

        if (laptop.getUser().getUsername().equals(user.getUsername())) {
            return true;
        }

        return false;
    }
}
