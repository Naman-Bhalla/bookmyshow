package com.bookmyshow.app.repositories.interfaces;

import com.bookmyshow.app.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByPhone(String phone);

    Optional<Customer> findCustomerByEmail(String email);

    List<Customer> findAllByEmailContaining(String containingWord);

    // customerRepository.findAllBYEmailContaining('@gmail.com);
    // Optional<>
    // Optional<> There is never a null in your system
}
