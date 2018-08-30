package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.SmartPhone;

public interface SmartphoneService {
    SmartPhone findById(String id);

    void saveSmartphone(SmartPhone smartPhone);
}
