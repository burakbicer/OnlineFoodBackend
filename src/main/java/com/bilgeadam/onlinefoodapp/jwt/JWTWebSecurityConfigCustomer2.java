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
        httpSecurity
                .rememberMe().key("uniqueAndSecret")
                .rememberMeParameter("remember") //Name of checkbox at login page
                .rememberMeCookieName("rememberLoginInfo") //Cookie name
                .tokenValiditySeconds(300000); //Remember login credentials for number of seconds
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers(HttpMethod.POST, "/customer2/authenticate2")
                .antMatchers(HttpMethod.POST, "/customer2/refresh2")
                .antMatchers(HttpMethod.POST, "/orders/changeOrderStatus")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .and()
                .ignoring()
                .antMatchers(HttpMethod.GET, "/");
    }
}