package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.BaseProduct;

public interface CloudinaryService {
    void saveImage(BaseProduct product);

    void deleteImage(BaseProduct product);
}
