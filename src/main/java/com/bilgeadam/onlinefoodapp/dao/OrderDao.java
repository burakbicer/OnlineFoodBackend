package com.bilgeadam.onlinefoodapp.dao;

import com.bilgeadam.onlinefoodapp.domain.Order;

import java.util.List;

public interface OrderDao { // Order repo
    List<Order> getCustomerOrderHistory(Long customerId);
}
