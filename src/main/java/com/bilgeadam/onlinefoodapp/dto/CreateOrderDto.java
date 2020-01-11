package com.bilgeadam.onlinefoodapp.dto;

// burada  username si bu olan kullanıcı şu meal idleri yemek olarak sipariş veriyor
// add remove kısmındaki veri yapısı

public class CreateOrderDto {
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
