package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.models.binding.SmartphoneRemoveBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.SmartphoneUpdateBindingModel;

import java.util.List;

public interface SmartphoneService {
    void saveSmartphone(SmartPhone smartphone);

    void updateSmartphone(SmartphoneUpdateBindingModel smartphoneUpdateBindingModel);

    void removeSmartphone(SmartphoneRemoveBindingModel smartphoneRemoveBindingModel);

    void deleteAllRejected();

    List<SmartPhone> findAllRejected();
}
