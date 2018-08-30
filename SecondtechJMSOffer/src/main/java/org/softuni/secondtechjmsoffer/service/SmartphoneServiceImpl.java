package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.domain.entities.User;
import org.softuni.secondtechjmsoffer.domain.entities.UserRole;
import org.softuni.secondtechjmsoffer.domain.enums.ProductStatus;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneUpdateBindingModel;
import org.softuni.secondtechjmsoffer.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    @Autowired
    public SmartphoneServiceImpl(SmartphoneRepository smartphoneRepository, CloudinaryService cloudinaryService, UserService userService) {
        this.smartphoneRepository = smartphoneRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @Override
    public void saveSmartphone(SmartPhone smartphone) {
        this.cloudinaryService.saveImage(smartphone);
        this.smartphoneRepository.saveAndFlush(smartphone);
    }

    @Override
    public void updateSmartphone(SmartphoneUpdateBindingModel smartphoneUpdateBindingModel) {
        SmartPhone smartphone = this.smartphoneRepository.findById(smartphoneUpdateBindingModel.getId()).orElse(null);
        if (smartphone != null) {
            smartphone.setStatus(ProductStatus.valueOf(smartphoneUpdateBindingModel.getStatus()));
            this.smartphoneRepository.saveAndFlush(smartphone);
        }
    }

    @Override
    public void removeSmartphone(SmartphoneRemoveBindingModel smartphoneRemoveBindingModel) {
        User user = this.userService.getUserByUsername(smartphoneRemoveBindingModel.getUsername());
        SmartPhone smartphone = this.smartphoneRepository.findById(smartphoneRemoveBindingModel.getId()).orElse(null);
        if (checkAuthority(user, smartphone)) {
            this.cloudinaryService.deleteImage(smartphone);
            this.smartphoneRepository.delete(smartphone);
        }
    }

    @Override
    public void deleteAllRejected() {
        this.smartphoneRepository.deleteAllByStatus(ProductStatus.REJECTED);
    }

    @Override
    public List<SmartPhone> findAllRejected() {
        return this.smartphoneRepository.findAllByStatus(ProductStatus.REJECTED);
    }

    private boolean checkAuthority(User user, SmartPhone smartphone) {

        if (user == null || smartphone == null) {
            return false;
        }

        for (UserRole role : user.getAuthorities()) {
            if (role.getAuthority().equals("MODERATOR")) {
                return true;
            }
        }

        if (smartphone.getUser().getUsername().equals(user.getUsername())) {
            return true;
        }

        return false;
    }
}
