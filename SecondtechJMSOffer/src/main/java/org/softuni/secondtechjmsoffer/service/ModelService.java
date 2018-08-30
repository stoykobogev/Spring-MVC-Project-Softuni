package org.softuni.secondtechjmsoffer.service;

import org.softuni.secondtechjmsoffer.domain.entities.Laptop;
import org.softuni.secondtechjmsoffer.domain.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.domain.entities.Tablet;
import org.softuni.secondtechjmsoffer.domain.models.binding.LaptopOfferBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.SmartphoneOfferBindingModel;
import org.softuni.secondtechjmsoffer.domain.models.binding.TabletOfferBindingModel;

public interface ModelService {
    Tablet mapTabletOfferBindingModelToTablet(TabletOfferBindingModel tabletOfferBindingModel);

    Laptop mapLaptopOfferBindingModelToLaptop(LaptopOfferBindingModel laptopOfferBindingModel);

    SmartPhone mapSmartphoneOfferBindingModelToSmartphone(SmartphoneOfferBindingModel smartphoneOfferBindingModel);
}
