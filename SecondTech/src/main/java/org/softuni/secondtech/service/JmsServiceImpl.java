package org.softuni.secondtech.service;

import org.softuni.secondtech.domain.models.binding.LaptopRemoveBindingModel;
import org.softuni.secondtech.domain.models.binding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class JmsServiceImpl implements JmsService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendComment(CommentBindingModel commentBindingModel) {
        this.jmsTemplate.convertAndSend("comments", commentBindingModel);
    }

    @Override
    public void sendOrder(OrderBindingModel orderBindingModel) {
        this.jmsTemplate.convertAndSend("orders", orderBindingModel);
    }

    @Override
    public void sendComment(CommentUpdateBindingModel commentUpdateBindingModel) {
        this.jmsTemplate.convertAndSend("comments-update", commentUpdateBindingModel);
    }

    @Override
    public void sendLaptopOffer(LaptopOfferBindingModel laptopOfferBindingModel, Principal principal) {
        laptopOfferBindingModel.setAddedOn(LocalDateTime.now());
        laptopOfferBindingModel.setUsername(principal.getName());
        this.jmsTemplate.convertAndSend("offers-laptop", laptopOfferBindingModel);
    }

    @Override
    public void sendLaptopOfferUpdate(LaptopUpdateBindingModel laptopUpdateBindingModel) {
        this.jmsTemplate.convertAndSend("offers-laptop-update", laptopUpdateBindingModel);
    }

    @Override
    public void sendLaptopOfferRemove(String id, Principal principal) {
        if (id == null || principal == null) return;

        LaptopRemoveBindingModel laptopRemoveBindingModel = new LaptopRemoveBindingModel();
        laptopRemoveBindingModel.setId(id);
        laptopRemoveBindingModel.setUsername(principal.getName());
        this.jmsTemplate.convertAndSend("offers-laptop-remove", laptopRemoveBindingModel);
    }

    @Override
    public void sendSmartphoneOffer(SmartphoneOfferBindingModel smartphoneOfferBindingModel, Principal principal) {
        smartphoneOfferBindingModel.setAddedOn(LocalDateTime.now());
        smartphoneOfferBindingModel.setUsername(principal.getName());
        this.jmsTemplate.convertAndSend("offers-smartphone", smartphoneOfferBindingModel);
    }

    @Override
    public void sendSmartphoneOfferUpdate(SmartphoneUpdateBindingModel smartphoneUpdateBindingModel) {
        this.jmsTemplate.convertAndSend("offers-smartphone-update", smartphoneUpdateBindingModel);
    }

    @Override
    public void sendSmartphoneOfferRemove(String id, Principal principal) {
        if (id == null || principal == null) return;

        SmartphoneRemoveBindingModel smartphoneRemoveBindingModel = new SmartphoneRemoveBindingModel();
        smartphoneRemoveBindingModel.setId(id);
        smartphoneRemoveBindingModel.setUsername(principal.getName());
        this.jmsTemplate.convertAndSend("offers-smartphone-remove", smartphoneRemoveBindingModel);
    }

    @Override
    public void sendTabletOffer(TabletOfferBindingModel tabletOfferBindingModel, Principal principal) {
        tabletOfferBindingModel.setAddedOn(LocalDateTime.now());
        tabletOfferBindingModel.setUsername(principal.getName());
        this.jmsTemplate.convertAndSend("offers-tablet", tabletOfferBindingModel);
    }

    @Override
    public void sendTabletOfferUpdate(TabletUpdateBindingModel tabletUpdateBindingModel) {
        this.jmsTemplate.convertAndSend("offers-tablet-update", tabletUpdateBindingModel);
    }

    @Override
    public void sendTabletOfferRemove(String id, Principal principal) {
        if (id == null || principal == null) return;

        TabletRemoveBindingModel tabletRemoveBindingModel = new TabletRemoveBindingModel();
        tabletRemoveBindingModel.setId(id);
        tabletRemoveBindingModel.setUsername(principal.getName());
        this.jmsTemplate.convertAndSend("offers-tablet-remove", tabletRemoveBindingModel);
    }
}
