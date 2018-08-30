package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.Laptop;
import org.softuni.secondtechjmsorder.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public Laptop findById(String id) {
        return this.laptopRepository.findById(id).orElse(null);
    }

    @Override
    public void saveLaptop(Laptop laptop) {
        this.laptopRepository.saveAndFlush(laptop);
    }
}
