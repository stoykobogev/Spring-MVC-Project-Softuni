package org.softuni.secondtechjmsoffer.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.secondtechjmsoffer.enums.ProductStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    protected String id;

    @Column
    protected String name;

    @Column(nullable = false, scale = 2)
    protected BigDecimal price;

    @Column
    protected String description;

    @Column
    @Enumerated(EnumType.STRING)
    protected ProductStatus status;

    @Column
    protected String cpu;

    @Column
    protected String display;

    @Column
    protected String memory;

    @Column
    protected String imageUrl;

    @Column
    protected String imageId;

    @Column
    protected String ram;

    @Column
    protected String OS;

    @Column(columnDefinition = "DATETIME")
    protected LocalDateTime addedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    protected User user;

    protected BaseProduct() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return this.status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
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

    public LocalDateTime getAddedOn() {
        return this.addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
