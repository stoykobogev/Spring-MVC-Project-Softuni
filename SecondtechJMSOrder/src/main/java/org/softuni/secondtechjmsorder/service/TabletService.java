package org.softuni.secondtechjmsorder.service;

import org.softuni.secondtechjmsorder.domain.entities.Tablet;

public interface TabletService {
    Tablet findById(String id);

    void saveTablet(Tablet tablet);
}
