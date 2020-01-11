package com.bilgeadam.onlinefoodapp.jwt;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.domain.Employee;
import com.bilgeadam.onlinefoodapp.repo.EmployeeServiceImpl;
import com.bilgeadam.onlinefoodapp.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// bu config dosyaları tamamıyla ilişkili customer jwt si için gerekli filelari olusturdum
// login olurken kullanıcıyı sessiona ekleyip token üretiyor

@Service
public class JwtUserDetailsCustomer2Service implements UserDetailsService {

    private final CustomerService customerService;

    public JwtUserDetailsCustomer2Service(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer userFromDB = customerService.findByUsername(username);
        JwtUserDetails2 jwtUserDetails = null;
        if (userFromDB != null) {
            jwtUserDetails = new JwtUserDetails2(userFromDB);
        } else {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }
        return jwtUserDetails;
    }
}



















