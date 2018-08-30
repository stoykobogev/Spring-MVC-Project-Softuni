package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneUpdateBindingModel;

import java.util.List;

public interface SmartphoneService {
    void saveSmartphone(SmartPhone smartphone);

    void updateSmartphone(SmartphoneUpdateBindingModel smartphoneUpdateBindingModel);

    void removeSmartphone(SmartphoneRemoveBindingModel smartphoneRemoveBindingModel);

    void deleteAllRejected();

    List<SmartPhone> findAllRejected();
}
