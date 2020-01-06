package com.bilgeadam.onlinefoodapp.dto;

import com.bilgeadam.onlinefoodapp.domain.Customer;

public class CreateCustomerDto {
    private String name;
    private String surname;
    private String username;
    private String password;


    public Customer dtoToCustomer() {
        return new Customer(
                this.getName(),
                this.getSurname(),
                this.getUsername(),
                this.getPassword()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
