package org.softuni.secondtechjmsoffer.listeners;

import org.softuni.secondtechjmsoffer.entities.Tablet;
import org.softuni.secondtechjmsoffer.models.binding.TabletOfferBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.TabletRemoveBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.TabletUpdateBindingModel;
import org.softuni.secondtechjmsoffer.services.ModelService;
import org.softuni.secondtechjmsoffer.services.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TabletOfferListener {

    private final TabletService tabletService;
    private final ModelService modelService;

    @Autowired
    public TabletOfferListener(TabletService tabletService, ModelService modelService) {
        this.tabletService = tabletService;
        this.modelService = modelService;
    }

    @JmsListener(destination = "offers-tablet", containerFactory = "myFactory")
    public void receiveTabletOffer(TabletOfferBindingModel tabletOfferBindingModel) {
        Tablet tablet = this.modelService.mapTabletOfferBindingModelToTablet(tabletOfferBindingModel);
        if (tablet != null) {
            this.tabletService.saveTablet(tablet);
        }
    }

    @JmsListener(destination = "offers-tablet-update", containerFactory = "myFactory")
    public void receiveTabletOffer(TabletUpdateBindingModel tabletUpdateBindingModel) {
        this.tabletService.updateTablet(tabletUpdateBindingModel);
    }

    @JmsListener(destination = "offers-tablet-remove", containerFactory = "myFactory")
    public void receiveTabletOffer(TabletRemoveBindingModel tabletRemoveBindingModel) {
        this.tabletService.removeTablet(tabletRemoveBindingModel);
    }
}