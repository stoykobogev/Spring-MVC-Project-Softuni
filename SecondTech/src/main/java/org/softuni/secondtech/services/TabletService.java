package org.softuni.secondtech.services;

import org.softuni.secondtech.entities.Tablet;
import org.softuni.secondtech.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TabletService {
    List<Tablet> findAllApproved();

    Page<Tablet> getPageWithApproved(Pageable pageable);

    Tablet findApprovedById(String id);

    List<Tablet> findAllPending();

    List<Tablet> findAllByUsername(String username);

    List<Tablet> findAllByUser(User user);

    @Transactional
    void deleteAllByUser(User user);

    void saveTablet(Tablet tablet);
}
