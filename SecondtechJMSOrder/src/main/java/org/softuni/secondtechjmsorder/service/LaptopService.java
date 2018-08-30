package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.Laptop;

public interface LaptopService {
    Laptop findById(String id);

    void saveLaptop(Laptop laptop);
}
