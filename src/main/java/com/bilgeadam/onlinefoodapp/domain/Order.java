package com.bilgeadam.onlinefoodapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
//bunlar da veritabanu modellleri
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    @JsonIgnore
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "orders_meals",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "code"))
    @JsonIgnore
    private List<Meal> mealList;

    @Column(name = "order_status")
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @Column(name = "ordered_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm MM-dd-yyyy",timezone = "Europe/Istanbul")
    private Date orderedDate;

    public Order() {
    }

    public Order(Customer customer, List<Meal> mealList, OrderStatus orderStatus, Date orderedDate) {
        this.customer = customer;
        this.mealList = mealList;
        this.orderStatus = orderStatus;
        this.orderedDate = orderedDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
