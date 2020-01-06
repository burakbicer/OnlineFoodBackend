package com.bilgeadam.onlinefoodapp.service;

import com.bilgeadam.onlinefoodapp.dao.OrderDaoImpl;
import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.domain.OrderStatus;
import com.bilgeadam.onlinefoodapp.dto.ChangeOrderStatusDto;
import com.bilgeadam.onlinefoodapp.dto.CreateOrderDto;
import com.bilgeadam.onlinefoodapp.repo.CustomerRepository;
import com.bilgeadam.onlinefoodapp.repo.MealRepository;
import com.bilgeadam.onlinefoodapp.repo.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
// Bu da aynı eve
@Service
public class OrdersService {
    private final OrderDaoImpl orderDao;
    private final CustomerRepository customerRepository;
    private final MealRepository mealRepository;
    private final OrdersRepository ordersRepository;


    @Autowired
    public OrdersService(OrderDaoImpl orderDao, CustomerRepository customerRepository, MealRepository mealRepository, OrdersRepository ordersRepository) {
        this.orderDao = orderDao;
        this.customerRepository = customerRepository;
        this.mealRepository = mealRepository;
        this.ordersRepository = ordersRepository;
    }

    public List<Order> getCustomerOrderHistory(String customerUsername) {

        Customer customer = customerRepository.findByUsername(customerUsername);

        return orderDao.getCustomerOrderHistory(customer.getCustomerId());
    }

    public Order createOrder(CreateOrderDto createOrderDto) {
        Customer customer = customerRepository.findByUsername(createOrderDto.getUsername());
        
        List<Meal> mealList = new ArrayList<>();

        Arrays.stream(createOrderDto.getMealId()).forEach(mealId -> {
            mealList.add(mealRepository.findById(mealId).get());
        });

        Order order = new Order(customer, mealList, OrderStatus.PREPARING, new Date());
        return ordersRepository.save(order);
    }

    public Order changeOrderStatus(ChangeOrderStatusDto changeOrderStatusDto) {
        Order order = ordersRepository.getOne(changeOrderStatusDto.getOrderId());
        Arrays.stream(OrderStatus.values()).forEach(orderStatus -> {
            if (orderStatus.getValue().equals(changeOrderStatusDto.getOrderStatus())) {
                order.setOrderStatus(orderStatus);
            }
        });
        return ordersRepository.save(order);
    }
}
