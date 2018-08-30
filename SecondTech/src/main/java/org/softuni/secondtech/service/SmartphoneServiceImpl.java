package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.SmartPhone;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.softuni.secondtech.repository.SmartphoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private final UserService userService;

    public SmartphoneServiceImpl(SmartphoneRepository smartphoneRepository, UserService userService) {
        this.smartphoneRepository = smartphoneRepository;
        this.userService = userService;
    }

    @Override
    public List<SmartPhone> findAllApproved() {
        return this.smartphoneRepository.findAllByStatus(ProductStatus.APPROVED);
    }

    @Override
    public Page<SmartPhone> getPageWithApproved(Pageable pageable) {
        return this.smartphoneRepository.findAllByStatus(ProductStatus.APPROVED, pageable);
    }

    @Override
    public SmartPhone findApprovedById(String id) {
        return this.smartphoneRepository.findByIdAndStatus(id, ProductStatus.APPROVED);
    }

    @Override
    public List<SmartPhone> findAllPending() {
        return this.smartphoneRepository.findAllByStatus(ProductStatus.PENDING);
    }

    @Override
    public List<SmartPhone> findAllByUsername(String username) {
        User user = this.userService.getUserByUsername(username);
        if (user != null) {
            return this.smartphoneRepository.findAllByUser(user);
        }

        return null;
    }

    @Override
    public List<SmartPhone> findAllByUser(User user) {
        return this.smartphoneRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteAllByUser(User user) {
        this.smartphoneRepository.deleteAllByUser(user);
    }

    @Override
    public void saveSmartphone(SmartPhone smartPhone) {
        this.smartphoneRepository.saveAndFlush(smartPhone);
    }

    @Override
    public void removeUserFromSmartphones(User user) {
        List<SmartPhone> smartPhoneList = findAllByUser(user);
        removeUserFromSmartphones(user, smartPhoneList);
    }

    @Override
    public void removeUserFromSmartphones(User user, List<SmartPhone> smartPhoneList) {
        if (smartPhoneList != null && user != null) {
            for (SmartPhone smartPhone : findAllByUser(user)) {
                smartPhone.setUser(null);
                saveSmartphone(smartPhone);
            }
        }
    }
}
