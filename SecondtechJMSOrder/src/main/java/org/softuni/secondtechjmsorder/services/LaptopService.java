package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.Laptop;

public interface LaptopService {
    Laptop findById(String id);

    void saveLaptop(Laptop laptop);
}
