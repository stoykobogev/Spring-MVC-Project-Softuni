package org.softuni.secondtechjmsoffer.services;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.softuni.secondtechjmsoffer.entities.*;
import org.softuni.secondtechjmsoffer.enums.ProductStatus;
import org.softuni.secondtechjmsoffer.models.binding.LaptopOfferBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.SmartphoneOfferBindingModel;
import org.softuni.secondtechjmsoffer.models.binding.TabletOfferBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        configMapper();
    }


    @Override
    public Laptop mapLaptopOfferBindingModelToLaptop(LaptopOfferBindingModel laptopOfferBindingModel) {
        User user = this.userService.getUserByUsername(laptopOfferBindingModel.getUsername());
        if (user == null) {
           return null;
        }

        Laptop laptop = this.modelMapper.map(laptopOfferBindingModel, Laptop.class);
        laptop.setUser(user);
        setProductStatus(laptop);

        return laptop;
    }

    @Override
    public Tablet mapTabletOfferBindingModelToTablet(TabletOfferBindingModel tabletOfferBindingModel) {
        User user = this.userService.getUserByUsername(tabletOfferBindingModel.getUsername());
        if (user == null) {
            return null;
        }

        Tablet tablet = this.modelMapper.map(tabletOfferBindingModel, Tablet.class);
        tablet.setUser(user);
        setProductStatus(tablet);

        return tablet;
    }

    @Override
    public SmartPhone mapSmartphoneOfferBindingModelToSmartphone(SmartphoneOfferBindingModel smartphoneOfferBindingModel) {
        User user = this.userService.getUserByUsername(smartphoneOfferBindingModel.getUsername());
        if (user == null) {
            return null;
        }

        SmartPhone smartphone = this.modelMapper.map(smartphoneOfferBindingModel, SmartPhone.class);
        smartphone.setUser(user);
        setProductStatus(smartphone);

        return smartphone;
    }

    private void configMapper() {
        this.modelMapper.addConverter(new Converter<Long, BigDecimal>() {
            @Override
            public BigDecimal convert(MappingContext<Long, BigDecimal> context) {
                return new BigDecimal(context.getSource());
            }
        });
    }

    private void setProductStatus(BaseProduct product) {
        for (UserRole role : product.getUser().getAuthorities()) {
            if (role.getAuthority().equals("MODERATOR")) {
                product.setStatus(ProductStatus.APPROVED);
                return;
            }
        }

        product.setStatus(ProductStatus.PENDING);
    }
}
