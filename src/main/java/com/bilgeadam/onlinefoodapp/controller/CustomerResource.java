package com.bilgeadam.onlinefoodapp.controller;

// customer için controller burdan direk kayıt yapabiliriz
// create api sini kulanarak

import com.bilgeadam.onlinefoodapp.OnlinefoodappApplication;
import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.dto.CreateCustomerDto;
import com.bilgeadam.onlinefoodapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customer")
public class CustomerResource {

    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public Customer createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return customerService.createUser(createCustomerDto);
    }
}
