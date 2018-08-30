package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.SmartPhone;
import org.softuni.secondtech.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SmartphoneService {
    List<SmartPhone> findAllApproved();

    Page<SmartPhone> getPageWithApproved(Pageable pageable);

    SmartPhone findApprovedById(String id);

    List<SmartPhone> findAllPending();

    List<SmartPhone> findAllByUsername(String username);

    List<SmartPhone> findAllByUser(User user);

    @Transactional
    void deleteAllByUser(User user);

    void saveSmartphone(SmartPhone smartPhone);

    void removeUserFromSmartphones(User user);

    void removeUserFromSmartphones(User user, List<SmartPhone> smartPhoneList);
}
