package org.softuni.secondtechjmsoffer.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.softuni.secondtechjmsoffer.entities.BaseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final static String NO_IMAGE_URL =
            "https://res.cloudinary.com/secondtech/image/upload/v1535464227/no-image.png";
    private final static String NO_IMAGE_ID = "no-image";

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public void saveImage(BaseProduct product) {
        try {
            Map result = this.cloudinary.uploader().upload(product.getImageUrl(), ObjectUtils.emptyMap());
            product.setImageId(result.get("public_id").toString());
            product.setImageUrl(result.get("secure_url").toString());
        } catch (IOException e) {
            product.setImageUrl(NO_IMAGE_URL);
            product.setImageId(NO_IMAGE_ID);
        }
    }

    @Override
    public void deleteImage(BaseProduct product) {
        String imageId = product.getImageId();
        if (imageId == null || NO_IMAGE_ID.equals(imageId)) { return;}

        List<String> idList = new ArrayList<>();
        idList.add(imageId);
        try {
            this.cloudinary.api().deleteResources(idList, ObjectUtils.emptyMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
