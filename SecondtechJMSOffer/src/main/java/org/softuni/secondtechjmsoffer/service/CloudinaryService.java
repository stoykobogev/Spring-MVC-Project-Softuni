package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.BaseProduct;

public interface CloudinaryService {
    void saveImage(BaseProduct product);

    void deleteImage(BaseProduct product);
}
