package com.bilgeadam.onlinefoodapp.dto;

// Frontend den gelen obje yapısı dto altındaki hepsi

public class ChangeOrderStatusDto {
    private Long orderId;
    private String orderStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
