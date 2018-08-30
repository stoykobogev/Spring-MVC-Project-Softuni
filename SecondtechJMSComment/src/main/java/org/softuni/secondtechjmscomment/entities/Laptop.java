package org.softuni.secondtechjmscomment.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "laptops")
@DiscriminatorValue("LAPTOP")
public class Laptop extends BaseProduct {

    @Column
    private String GPU;

    public Laptop() {
    }

    public String getGPU() {
        return this.GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }
}
