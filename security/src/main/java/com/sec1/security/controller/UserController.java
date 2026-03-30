package com.sec1.security.controller;

import com.sec1.security.model.Customer;
import com.sec1.security.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            String hashPswd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPswd);
            Customer savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0)
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User details not saved successfully registered");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured: " + e.getMessage());
        }
    }

}
