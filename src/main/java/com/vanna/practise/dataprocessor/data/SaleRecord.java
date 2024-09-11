package com.vanna.practise.dataprocessor.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleRecord {

    private long orderId;
    private LocalDate orderDate;
    private BigDecimal revenue;

    public SaleRecord(long orderId, LocalDate orderDate, BigDecimal revenue) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.revenue = revenue;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}
