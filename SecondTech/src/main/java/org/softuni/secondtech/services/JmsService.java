package org.softuni.secondtech.services;

import org.softuni.secondtech.models.binding.*;

import java.security.Principal;

public interface JmsService {
    void sendComment(CommentBindingModel commentBindingModel);

    void sendOrder(OrderBindingModel orderBindingModel);

    void sendComment(CommentUpdateBindingModel commentUpdateBindingModel);

    void sendLaptopOffer(LaptopOfferBindingModel laptopOfferBindingModel, Principal principal);

    void sendSmartphoneOffer(SmartphoneOfferBindingModel smartphoneOfferBindingModel, Principal principal);

    void sendLaptopOfferUpdate(LaptopUpdateBindingModel laptopUpdateBindingModel);

    void sendTabletOffer(TabletOfferBindingModel tabletOfferBindingModel, Principal principal);

    void sendSmartphoneOfferUpdate(SmartphoneUpdateBindingModel smartphoneUpdateBindingModel);

    void sendLaptopOfferRemove(String id, Principal principal);

    void sendTabletOfferUpdate(TabletUpdateBindingModel tabletUpdateBindingModel);

    void sendSmartphoneOfferRemove(String id, Principal principal);

    void sendTabletOfferRemove(String id, Principal principal);
}
