package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
// bunu da eklemissin hocam
//bunların hepsi var projede
//bu da repository katmanı
//bunları bilmemn gerek senin
// dtaabase ye istek atılan katman burası
// anladım hocam

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);

    List<Customer> findAll ();
}
