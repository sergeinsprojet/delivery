package com.express.delivery.controller;

import com.express.delivery.domain.Customer;
import com.express.delivery.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerWebController {
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/api/1.0/customer/list")
    public List<Customer> listApi(@RequestParam(value="name", defaultValue="World") String name) {
        return customerRepository.findAll();
    }
}
