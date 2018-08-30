package org.softuni.web.listeners;

import org.softuni.secondtechjmsoffer.domain.entities.Laptop;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopOfferBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopRemoveBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopUpdateBindingModel;
import org.softuni.secondtechjmsoffer.service.LaptopService;
import org.softuni.secondtechjmsoffer.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class LaptopOfferListener {

    private final ModelService modelService;
    private final LaptopService laptopService;

    @Autowired
    public LaptopOfferListener(ModelService modelService, LaptopService laptopService) {
        this.modelService = modelService;
        this.laptopService = laptopService;
    }

    @JmsListener(destination = "offers-laptop", containerFactory = "myFactory")
    public void receiveLaptopOffer(LaptopOfferBindingModel laptopOfferBindingModel) {
        Laptop laptop = this.modelService.mapLaptopOfferBindingModelToLaptop(laptopOfferBindingModel);
        if (laptop != null) {
            this.laptopService.saveLaptop(laptop);
        }
    }

    @JmsListener(destination = "offers-laptop-update", containerFactory = "myFactory")
    public void receiveLaptopOffer(LaptopUpdateBindingModel laptopUpdateBindingModel) {
        this.laptopService.updateLaptop(laptopUpdateBindingModel);
    }

    @JmsListener(destination = "offers-laptop-remove", containerFactory = "myFactory")
    public void receiveLaptopOffer(LaptopRemoveBindingModel laptopRemoveBindingModel) {
        this.laptopService.removeLaptop(laptopRemoveBindingModel);
    }
}
