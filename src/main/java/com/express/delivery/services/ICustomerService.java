package com.express.delivery.services;

import com.express.delivery.domain.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> findAll();
}
