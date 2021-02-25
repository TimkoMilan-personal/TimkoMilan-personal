package com.warehouse.warehouse.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VENDOR")
public class Vendor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Date dateCreation;
    private Boolean isOrdered;

    public Vendor(Long productId, Date dateCreation, Boolean isOrdered) {
        this.productId = productId;
        this.dateCreation = dateCreation;
        this.isOrdered = isOrdered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getOrdered() {
        return isOrdered;
    }

    public void setOrdered(Boolean ordered) {
        isOrdered = ordered;
    }
}
