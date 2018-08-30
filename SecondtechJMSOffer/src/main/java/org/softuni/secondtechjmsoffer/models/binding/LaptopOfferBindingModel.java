package org.softuni.secondtechjmsoffer.models.binding;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class LaptopOfferBindingModel {


    private String name;
    private Long price;
    private String description;
    private String cpu;
    private String display;
    private String memory;
    private String imageUrl;
    private String ram;
    private String OS;
    private String GPU;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime addedOn;
    private String username;

    public LaptopOfferBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCpu() {
        return this.cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getMemory() {
        return this.memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRam() {
        return this.ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOS() {
        return this.OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getGPU() {
        return this.GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public LocalDateTime getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
