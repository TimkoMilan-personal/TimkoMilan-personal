package com.warehouse.warehouse.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;
    private Date createRecordDate;
    private Date confirmOrderDate;
    private boolean isOrdered;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateRecordDate() {
        return createRecordDate;
    }

    public void setCreateRecordDate(Date createRecordDate) {
        this.createRecordDate = createRecordDate;
    }

    public Date getConfirmOrderDate() {
        return confirmOrderDate;
    }

    public void setConfirmOrderDate(Date confirmOrderDate) {
        this.confirmOrderDate = confirmOrderDate;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
