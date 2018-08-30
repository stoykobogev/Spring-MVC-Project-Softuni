package org.softuni.secondtechjmscomment.services;

import org.softuni.secondtechjmscomment.entities.SmartPhone;
import org.softuni.secondtechjmscomment.repositories.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;

    @Autowired
    public SmartphoneServiceImpl(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    @Override
    public SmartPhone findById(String id) {
        return this.smartphoneRepository.findById(id).orElse(null);
    }
}
