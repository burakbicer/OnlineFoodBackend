package com.bilgeadam.onlinefoodapp.jwt.resource;

import com.bilgeadam.onlinefoodapp.jwt.JwtTokenUtil;
import com.bilgeadam.onlinefoodapp.jwt.JwtUserDetails;
import com.bilgeadam.onlinefoodapp.jwt.JwtUserDetailsAdminService;
import com.bilgeadam.onlinefoodapp.jwt.JwtUserDetailsCustomer2Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/customer2")
public class JwtAuthenticationCustomer2Controller {

    private final AuthenticationManager authenticationManagerCustomer;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsCustomer2Service jwtUserDetailsCustomer2Service;

    public JwtAuthenticationCustomer2Controller(@Qualifier("authenticationManagerCustomer") AuthenticationManager authenticationManagerCustomer,
                                                JwtTokenUtil jwtTokenUtil,
                                                JwtUserDetailsAdminService jwtUserDetailsAdminService,
                                                JwtUserDetailsCustomer2Service jwtUserDetailsCustomer2Service) {
        this.authenticationManagerCustomer = authenticationManagerCustomer;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsCustomer2Service = jwtUserDetailsCustomer2Service;
    }

    @RequestMapping(value = "/authenticate2", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {
        final UserDetails userDetails = jwtUserDetailsCustomer2Service.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, "customer2");
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @RequestMapping(value = "/refresh2", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(@RequestBody String token) {
        token = token.substring(10, token.length()-2);
        try {
            if (jwtTokenUtil.isTokenExpired(token)) {
                String refreshedToken = jwtTokenUtil.refreshToken(token);
                return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
            } else return ResponseEntity.ok(new JwtTokenResponse(token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManagerCustomer.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}