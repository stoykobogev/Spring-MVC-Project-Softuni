package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.Laptop;
import org.softuni.secondtech.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LaptopService {

    List<Laptop> findAllApproved();

    Page<Laptop> getPageWithApproved(Pageable pageable);

    Laptop findApprovedById(String id);

    List<Laptop> findAllPending();

    List<Laptop> findAllByUsername(String username);

    List<Laptop> findAllByUser(User user);

    void saveLaptop(Laptop laptop);

    @Transactional
    void deleteAllByUser(User user);

    void removeUserFromLaptops(User user);

    void removeUserFromLaptops(User user, List<Laptop> laptopList);
}
