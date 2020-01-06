package com.bilgeadam.onlinefoodapp.controller;

//comment olarak yazacağım sen sonradan düzenlersin burası customer için controler burdan direk kayıt yapavilirsin
//create api sini kulanarak
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
