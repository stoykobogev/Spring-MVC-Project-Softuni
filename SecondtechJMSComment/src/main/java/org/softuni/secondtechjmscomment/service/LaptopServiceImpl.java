package org.softuni.secondtechjmscomment.service;

import org.softuni.secondtechjmscomment.domain.entities.Laptop;
import org.softuni.secondtechjmscomment.repository.LaptopRepository;
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
}
