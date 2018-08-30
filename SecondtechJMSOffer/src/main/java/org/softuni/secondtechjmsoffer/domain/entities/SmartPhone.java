package org.softuni.secondtechjmsoffer.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "smartphones")
public class SmartPhone extends BaseProduct {

    @Column
    private String battery;

    @Column
    private String camera;

    @Column
    private String SIM;

    public SmartPhone() {
    }

    public String getBattery() {
        return this.battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getSIM() {
        return this.SIM;
    }

    public void setSIM(String SIM) {
        this.SIM = SIM;
    }
}
