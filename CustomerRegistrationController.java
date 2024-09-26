package com.example.demo.controller;

import com.example.demo.model.CustomerRegistration;
import com.example.demo.repository.CustomerRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerRegistrationController {

    @Autowired
    private CustomerRegistrationRepository registrationRepository;

    // Register a new customer
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistration registration) {
        // Save customer registration data
        registrationRepository.save(registration);
        return ResponseEntity.ok("Customer registered successfully with Registration ID: " + registration.getRegistrationId());
    }

    // Cancel customer registration
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelRegistration(@RequestBody CustomerRegistration request) {
        // Find customer registration
        CustomerRegistration registration = registrationRepository
                .findByCustomerIdAndRegistrationId(request.getCustomerId(), request.getRegistrationId());

        if (registration == null) {
            return ResponseEntity.badRequest().body("Registration not found");
        }

        // Cancel the registration by deleting it from the DB
        registrationRepository.delete(registration);
        return ResponseEntity.ok("Customer registration cancelled for Registration ID: " + request.getRegistrationId());
    }
}
