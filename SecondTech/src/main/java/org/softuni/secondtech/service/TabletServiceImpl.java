package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.Tablet;
import org.softuni.secondtech.domain.entities.User;
import org.softuni.secondtech.domain.enums.ProductStatus;
import org.softuni.secondtech.repository.TabletRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TabletServiceImpl implements TabletService {

    private final TabletRepository tabletRepository;
    private final UserService userService;

    public TabletServiceImpl(TabletRepository tabletRepository, UserService userService) {
        this.tabletRepository = tabletRepository;
        this.userService = userService;
    }

    @Override
    public List<Tablet> findAllApproved() {
        return this.tabletRepository.findAllByStatus(ProductStatus.APPROVED);
    }

    @Override
    public Page<Tablet> getPageWithApproved(Pageable pageable) {
        return this.tabletRepository.findAllByStatus(ProductStatus.APPROVED, pageable);
    }

    @Override
    public Tablet findApprovedById(String id) {
        return this.tabletRepository.findByIdAndStatus(id, ProductStatus.APPROVED);
    }

    @Override
    public List<Tablet> findAllPending() {
        return this.tabletRepository.findAllByStatus(ProductStatus.PENDING);
    }

    @Override
    public List<Tablet> findAllByUsername(String username) {
        User user = this.userService.getUserByUsername(username);
        if (user != null) {
            return this.tabletRepository.findAllByUser(user);
        }

        return null;
    }

    @Override
    public List<Tablet> findAllByUser(User user) {
        return this.tabletRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteAllByUser(User user) {
        this.tabletRepository.deleteAllByUser(user);
    }

    @Override
    public void saveTablet(Tablet tablet) {
        this.tabletRepository.saveAndFlush(tablet);
    }

    @Override
    public void removeUserFromTablets(User user) {
        List<Tablet> tabletList = findAllByUser(user);
        removeUserFromTablets(user, tabletList);
    }

    @Override
    public void removeUserFromTablets(User user, List<Tablet> tabletList) {
        if (tabletList != null && user != null) {
            for (Tablet tablet : findAllByUser(user)) {
                tablet.setUser(null);
                saveTablet(tablet);
            }
        }
    }
}
