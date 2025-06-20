package com.example.productservice.model;

import java.math.BigDecimal;

public class Coupon {
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", expDate='" + expDate + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    private Long id;

    public Coupon(Long id, String code, BigDecimal discount, String expDate) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.expDate = expDate;
    }

    public Coupon() {
    }

    private String code;
    private BigDecimal discount;
    private String expDate;
}
