package com.bilgeadam.onlinefoodapp.service;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.dto.CreateCustomerDto;
import com.bilgeadam.onlinefoodapp.repo.CustomerRepository;
import com.bilgeadam.onlinefoodapp.repo.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByUsername(String username) {
//        List<Customer> x = customerRepository.findAll();
//        for (Customer c : x) {
//            System.out.println(c);
//        }

        return customerRepository.findByUsername(username);
    }

    public Customer createUser(CreateCustomerDto createCustomerDto) {
        Customer customer = createCustomerDto.dtoToCustomer();
        return customerRepository.save(customer);
    }
}
