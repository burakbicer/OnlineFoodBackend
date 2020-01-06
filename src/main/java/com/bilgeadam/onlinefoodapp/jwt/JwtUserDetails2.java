package com.bilgeadam.onlinefoodapp.jwt;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.domain.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//customer için ekledim ayrı config olsun diye
public class JwtUserDetails2 implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
//diğeriyle aynı fakat değişik kısımları var
    JwtUserDetails2(Customer customer) {
        this.id = customer.getCustomerId();
        this.username = customer.getUsername();
        this.password = customer.getPassword();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        customer.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        this.authorities = authorities;
    }

    JwtUserDetails2(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}