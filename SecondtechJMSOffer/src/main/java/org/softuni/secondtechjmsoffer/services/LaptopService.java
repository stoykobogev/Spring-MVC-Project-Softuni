package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.Laptop;
import org.softuni.secondtechjmsoffer.models.binding.LaptopRemoveBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.LaptopUpdateBindingModel;

import java.util.List;

public interface LaptopService {
    void saveLaptop(Laptop laptop);

    void updateLaptop(LaptopUpdateBindingModel laptopUpdateBindingModel);

    void removeLaptop(LaptopRemoveBindingModel laptopRemoveBindingModel);

    void deleteAllRejected();

    List<Laptop> findAllRejected();
}
