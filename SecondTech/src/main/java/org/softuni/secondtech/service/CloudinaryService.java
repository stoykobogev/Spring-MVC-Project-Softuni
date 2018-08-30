package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.entities.BaseProduct;

import java.util.List;

public interface CloudinaryService {

    void deleteImage(BaseProduct product);

    void deleteImages(List<? extends BaseProduct> productList);
}
