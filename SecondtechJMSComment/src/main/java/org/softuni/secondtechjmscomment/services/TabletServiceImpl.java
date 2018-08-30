package org.softuni.secondtechjmscomment.services;

import org.softuni.secondtechjmscomment.entities.Tablet;
import org.softuni.secondtechjmscomment.repositories.TabletRepository;
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
}
