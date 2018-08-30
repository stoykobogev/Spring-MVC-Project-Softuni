package org.softuni.secondtechjmscomment.service;

import org.softuni.secondtechjmscomment.domain.entities.BaseProduct;
import org.softuni.secondtechjmscomment.domain.entities.Comment;
import org.softuni.secondtechjmscomment.domain.entities.User;
import org.softuni.secondtechjmscomment.domain.enums.CommentStatus;
import org.softuni.secondtechjmscomment.domain.models.binding.CommentBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {

    private final LaptopService laptopService;
    private final SmartphoneService smartphoneService;
    private final TabletService tabletService;
    private final UserService userService;

    @Autowired
    public ModelServiceImpl(LaptopService laptopService, SmartphoneService smartphoneService, TabletService tabletService, UserService userService) {
        this.laptopService = laptopService;
        this.smartphoneService = smartphoneService;
        this.tabletService = tabletService;
        this.userService = userService;
    }

    @Override
    public Comment mapCommentBindingModelToComment(CommentBindingModel commentBindingModel) {
        Comment comment = new Comment();
        User user = this.userService.getUserByUsername(commentBindingModel.getUsername());
        BaseProduct product = getProduct(commentBindingModel.getProductType(), commentBindingModel.getProductId());
        if (user == null || product == null) {
            return null;
        }
        comment.setContent(commentBindingModel.getContent());
        comment.setUser(user);
        comment.setProduct(product);
        comment.setStatus(CommentStatus.PENDING);

        return comment;
    }

    private BaseProduct getProduct(String productType, String id) {
        if (productType.equals("laptop")) {
            return this.laptopService.findById(id);
        } else if (productType.equals("smartphone")) {
            return this.smartphoneService.findById(id);
        } else if (productType.equals("tabler")) {
            return this.tabletService.findById(id);
        } else {
            return null;
        }
    }
}
