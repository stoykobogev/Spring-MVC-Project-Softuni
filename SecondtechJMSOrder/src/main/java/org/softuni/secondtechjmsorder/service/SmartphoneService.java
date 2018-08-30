package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.SmartPhone;

public interface SmartphoneService {
    SmartPhone findById(String id);

    void saveSmartphone(SmartPhone smartPhone);
}
