package com.express.delivery.repository;

import com.express.delivery.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public List<Customer> findAll();

    public Customer getById(Long id);
}