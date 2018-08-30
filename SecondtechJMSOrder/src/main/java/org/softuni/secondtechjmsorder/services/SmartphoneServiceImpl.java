package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.SmartPhone;
import org.softuni.secondtechjmsorder.repositories.SmartphoneRepository;
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

    @Override
    public void saveSmartphone(SmartPhone smartPhone) {
        this.smartphoneRepository.saveAndFlush(smartPhone);
    }
}
