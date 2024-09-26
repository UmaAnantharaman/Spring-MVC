package com.example.demo.repository;

import com.example.demo.model.CustomerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, Long> {

    CustomerRegistration findByCustomerIdAndRegistrationId(Long customerId, Long registrationId);

    public void delete(CustomerRegistration registration);
}
