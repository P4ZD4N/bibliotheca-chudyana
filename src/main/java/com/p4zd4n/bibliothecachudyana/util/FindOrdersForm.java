package com.p4zd4n.bibliothecachudyana.util;

import java.time.LocalDate;

public class FindOrdersForm {

    private Integer id;

    private String username;

    private LocalDate minDate;

    private LocalDate maxDate;

    private Double minTotalAmount;

    private Double maxTotalAmount;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    public Double getMinTotalAmount() {
        return minTotalAmount;
    }

    public void setMinTotalAmount(Double minTotalAmount) {
        this.minTotalAmount = minTotalAmount;
    }

    public Double getMaxTotalAmount() {
        return maxTotalAmount;
    }

    public void setMaxTotalAmount(Double maxTotalAmount) {
        this.maxTotalAmount = maxTotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
