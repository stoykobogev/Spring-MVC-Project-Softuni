package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.Laptop;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopUpdateBindingModel;

import java.util.List;

public interface LaptopService {
    void saveLaptop(Laptop laptop);

    void updateLaptop(LaptopUpdateBindingModel laptopUpdateBindingModel);

    void removeLaptop(LaptopRemoveBindingModel laptopRemoveBindingModel);

    void deleteAllRejected();

    List<Laptop> findAllRejected();
}
