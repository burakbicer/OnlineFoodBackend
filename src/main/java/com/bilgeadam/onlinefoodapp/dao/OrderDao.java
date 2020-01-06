package com.bilgeadam.onlinefoodapp.dao;

import com.bilgeadam.onlinefoodapp.domain.Order;

import java.util.List;

// hocam bunu anlatır mısın bu ne işe yarıyor
//burada repo aynısı
public interface OrderDao {
    List<Order> getCustomerOrderHistory(Long customerId);
}
