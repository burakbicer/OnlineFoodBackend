package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.dto.ChangeOrderStatusDto;
import com.bilgeadam.onlinefoodapp.dto.CreateOrderDto;
import com.bilgeadam.onlinefoodapp.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//order status change yaptığı ve historyu date ye göre sıraladığı api de burası
//controler request mapping i ne denir. mesela http://localhost:8080/create bi apidir.
//order create etttiğimiz kısım da burada
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/orders")
public class OrdersResource {

    private final OrdersService ordersService;

    @Autowired
    public OrdersResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/history/{customerUsername}")
    public List<Order> getCustomerOrderHistory(@PathVariable("customerUsername") String customerUsername) {
        return ordersService.getCustomerOrderHistory(customerUsername);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public Order createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return ordersService.createOrder(createOrderDto);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/changeOrderStatus")
    public Order changeOrderStatus(@RequestBody ChangeOrderStatusDto changeOrderStatusDto) {
        return ordersService.changeOrderStatus(changeOrderStatusDto);
    }

}
