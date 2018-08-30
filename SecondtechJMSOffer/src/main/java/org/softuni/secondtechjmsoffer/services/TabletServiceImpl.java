package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.Tablet;
import org.softuni.secondtechjmsoffer.entities.User;
import org.softuni.secondtechjmsoffer.entities.UserRole;
import org.softuni.secondtechjmsoffer.enums.ProductStatus;
import org.softuni.secondtechjmsoffer.models.binding.TabletRemoveBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.TabletUpdateBindingModel;
import org.softuni.secondtechjmsoffer.repositories.TabletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabletServiceImpl implements TabletService {

    private final TabletRepository tabletRepository;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    @Autowired
    public TabletServiceImpl(TabletRepository tabletRepository, CloudinaryService cloudinaryService, UserService userService) {
        this.tabletRepository = tabletRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @Override
    public void saveTablet(Tablet tablet) {
        this.cloudinaryService.saveImage(tablet);
        this.tabletRepository.saveAndFlush(tablet);
    }

    @Override
    public void updateTablet(TabletUpdateBindingModel tabletUpdateBindingModel) {
        Tablet tablet = this.tabletRepository.findById(tabletUpdateBindingModel.getId()).orElse(null);
        if (tablet != null) {
            tablet.setStatus(ProductStatus.valueOf(tabletUpdateBindingModel.getStatus()));
            this.tabletRepository.saveAndFlush(tablet);
        }
    }

    @Override
    public void removeTablet(TabletRemoveBindingModel tabletRemoveBindingModel) {
        User user = this.userService.getUserByUsername(tabletRemoveBindingModel.getUsername());
        Tablet tablet = this.tabletRepository.findById(tabletRemoveBindingModel.getId()).orElse(null);
        if (checkAuthority(user, tablet)) {
            this.cloudinaryService.deleteImage(tablet);
            this.tabletRepository.delete(tablet);
        }
    }

    @Override
    public void deleteAllRejected() {
        this.tabletRepository.deleteAllByStatus(ProductStatus.REJECTED);
    }

    @Override
    public List<Tablet> findAllRejected() {
        return this.tabletRepository.findAllByStatus(ProductStatus.REJECTED);
    }

    private boolean checkAuthority(User user, Tablet tablet) {

        if (user == null || tablet == null) {
            return false;
        }

        for (UserRole role : user.getAuthorities()) {
            if (role.getAuthority().equals("MODERATOR")) {
                return true;
            }
        }

        if (tablet.getUser().getUsername().equals(user.getUsername())) {
            return true;
        }

        return false;
    }
}
