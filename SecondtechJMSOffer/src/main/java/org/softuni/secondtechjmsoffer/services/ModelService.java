package org.softuni.secondtechjmsoffer.services;

import org.softuni.secondtechjmsoffer.entities.Laptop;
import org.softuni.secondtechjmsoffer.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.entities.Tablet;
import org.softuni.secondtechjmsoffer.models.binding.LaptopOfferBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.SmartphoneOfferBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.TabletOfferBindingModel;

public interface ModelService {
    Tablet mapTabletOfferBindingModelToTablet(TabletOfferBindingModel tabletOfferBindingModel);

    Laptop mapLaptopOfferBindingModelToLaptop(LaptopOfferBindingModel laptopOfferBindingModel);

    SmartPhone mapSmartphoneOfferBindingModelToSmartphone(SmartphoneOfferBindingModel smartphoneOfferBindingModel);
}
