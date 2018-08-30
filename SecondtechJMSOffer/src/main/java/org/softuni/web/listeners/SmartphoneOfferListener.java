package org.softuni.web.listeners;

import org.softuni.secondtechjmsoffer.domain.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneOfferBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneUpdateBindingModel;
import org.softuni.secondtechjmsoffer.service.ModelService;
import org.softuni.secondtechjmsoffer.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SmartphoneOfferListener {

    private final ModelService modelService;
    private final SmartphoneService smartphoneService;

    @Autowired
    public SmartphoneOfferListener(ModelService modelService, SmartphoneService smartphoneService) {
        this.modelService = modelService;
        this.smartphoneService = smartphoneService;
    }

    @JmsListener(destination = "offers-smartphone", containerFactory = "myFactory")
    public void receiveSmartphoneOffer(SmartphoneOfferBindingModel smartphoneOfferBindingModel) {
        SmartPhone smartphone = this.modelService.mapSmartphoneOfferBindingModelToSmartphone(smartphoneOfferBindingModel);
        if (smartphone != null) {
            this.smartphoneService.saveSmartphone(smartphone);
        }
    }

    @JmsListener(destination = "offers-smartphone-update", containerFactory = "myFactory")
    public void receiveSmartphoneOffer(SmartphoneUpdateBindingModel smartphoneUpdateBindingModel) {
        this.smartphoneService.updateSmartphone(smartphoneUpdateBindingModel);
    }

    @JmsListener(destination = "offers-smartphone-remove", containerFactory = "myFactory")
    public void receiveSmartphoneOffer(SmartphoneRemoveBindingModel smartphoneRemoveBindingModel) {
        this.smartphoneService.removeSmartphone(smartphoneRemoveBindingModel);
    }
}
