package org.softuni.secondtechjmsoffer.config;

import org.softuni.secondtechjmsoffer.domain.entities.Laptop;
import org.softuni.secondtechjmsoffer.domain.entities.SmartPhone;
import org.softuni.secondtechjmsoffer.domain.entities.Tablet;
import org.softuni.secondtechjmsoffer.service.CloudinaryService;
import org.softuni.secondtechjmsoffer.service.LaptopService;
import org.softuni.secondtechjmsoffer.service.SmartphoneService;
import org.softuni.secondtechjmsoffer.service.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class Scheduler {

    private final LaptopService laptopService;
    private final SmartphoneService smartphoneService;
    private final TabletService tabletService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public Scheduler(LaptopService laptopService, SmartphoneService smartphoneService,
                     TabletService tabletService, CloudinaryService cloudinaryService) {
        this.laptopService = laptopService;
        this.smartphoneService = smartphoneService;
        this.tabletService = tabletService;
        this.cloudinaryService = cloudinaryService;
    }

    //Weekly at Midnight, Sat to Sun
    @Scheduled(cron = "0 0 0 ? * Sun")
    public void deleteRejectedProducts() {
        for (Laptop laptop : this.laptopService.findAllRejected()) {
            this.cloudinaryService.deleteImage(laptop);
        }
        this.laptopService.deleteAllRejected();

        for (SmartPhone smartPhone : this.smartphoneService.findAllRejected()) {
            this.cloudinaryService.deleteImage(smartPhone);
        }
        this.smartphoneService.deleteAllRejected();

        for (Tablet tablet : this.tabletService.findAllRejected()) {
            this.cloudinaryService.deleteImage(tablet);
        }
        this.smartphoneService.deleteAllRejected();
    }
}
