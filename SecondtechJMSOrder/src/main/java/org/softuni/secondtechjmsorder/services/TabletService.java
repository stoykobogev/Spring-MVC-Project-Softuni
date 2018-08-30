package org.softuni.secondtechjmsorder.services;

import org.softuni.secondtechjmsorder.entities.Tablet;

public interface TabletService {
    Tablet findById(String id);

    void saveTablet(Tablet tablet);
}
