package com.bilgeadam.onlinefoodapp.dto;
// peki bunlar neden gerekli ?
//bunlar da postmandan ver frontend den gelen obje yapısı dto alntındaki hepsi
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
