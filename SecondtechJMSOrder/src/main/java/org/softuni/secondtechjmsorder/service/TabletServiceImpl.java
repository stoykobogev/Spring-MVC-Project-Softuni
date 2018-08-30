package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.Tablet;
import org.softuni.secondtechjmsorder.repository.TabletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TabletServiceImpl implements TabletService {

    private final TabletRepository tabletRepository;

    @Autowired
    public TabletServiceImpl(TabletRepository tabletRepository) {
        this.tabletRepository = tabletRepository;
    }

    @Override
    public Tablet findById(String id) {
        return this.tabletRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTablet(Tablet tablet) {
        this.tabletRepository.saveAndFlush(tablet);
    }
}
