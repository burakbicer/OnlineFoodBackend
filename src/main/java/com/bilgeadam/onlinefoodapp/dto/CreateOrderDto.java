package com.bilgeadam.onlinefoodapp.dto;
//burada  username si bu olan kulalnıcı şu meal idleri yemek olarak siariş veritor
//add remove kısmı vardı orasdaki veri yapısı aslında ...
public class CreateOrderDto {
//user bitden fazla yemek idsi seçiyor ya o aslında bak burası burdan geliyor
    // remove kısmı nerede hocam yazıyor
    private String username;
    private Long[] mealId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long[] getMealId() {
        return mealId;
    }

    public void setMealId(Long[] mealId) {
        this.mealId = mealId;
    }
}
