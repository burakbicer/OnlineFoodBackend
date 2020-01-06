package com.bilgeadam.onlinefoodapp.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//bu jwt ile olan configler sabit

/**
 antMatchers(HttpMethod.POST, "/customer2/authenticate2")
 .antMatchers(HttpMethod.POST, "/orders/changeOrderStatus")


 burda ki önemli ksım sen apilere tokensız ulaşamıyorsun
 //ilki token üretmek için diğer postmanda denemiştik ya statusü preapared olsun dite o kısım için passive cektim


 hocam jwt nedir unutmuşum anlatır mısın hocam
 senin yazdığın apileri daha gücenli hale getiryor bi text üretip onunla fronetend istek atar ve ona süre de eklyebiklirsin
 bunu sağlar token ise text t
 r uzun bir random üretilmiş tezt
 */
@Configuration
@Order(3)
@EnableWebSecurity
public class JWTWebSecurityConfigCustomer2 extends WebSecurityConfigurerAdapter {

    private final JwtUnauthorizedEntryPoint jwtUnauthorizedEntryPoint;
    private final JwtUserDetailsCustomer2Service jwtUserDetailsCustomer2Service;
    private final JwtTokenAuthorizationFilter jwtTokenAuthorizationFilter;

    public JWTWebSecurityConfigCustomer2(JwtUnauthorizedEntryPoint jwtUnauthorizedEntryPoint,
                                         JwtUserDetailsCustomer2Service jwtUserDetailsCustomerService,
                                         JwtTokenAuthorizationFilter jwtTokenAuthorizationFilter) {
        this.jwtUnauthorizedEntryPoint = jwtUnauthorizedEntryPoint;
        this.jwtUserDetailsCustomer2Service = jwtUserDetailsCustomerService;
        this.jwtTokenAuthorizationFilter = jwtTokenAuthorizationFilter;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsCustomer2Service).passwordEncoder(passwordEncoderBean());
    }

    @Bean(name = "passwordEncoderCustomer2")
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "authenticationManagerCustomer2")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtUnauthorizedEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(jwtTokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers(HttpMethod.POST, "/customer2/authenticate2")
                .antMatchers(HttpMethod.POST, "/orders/changeOrderStatus")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.GET, "/");
    }
}