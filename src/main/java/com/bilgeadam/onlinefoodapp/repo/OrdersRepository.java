package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// bu aynı şekilde sipariş kısmı aynen
@Repository
public interface OrdersRepository extends JpaRepository<Order,Long> {

    

}
