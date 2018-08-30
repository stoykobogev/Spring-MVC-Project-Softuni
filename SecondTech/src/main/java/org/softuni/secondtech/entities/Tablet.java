package org.softuni.secondtech.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tablets")
public class Tablet extends BaseProduct {

    @Column
    private String battery;

    @Column
    private String camera;

    public Tablet() {
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
}
