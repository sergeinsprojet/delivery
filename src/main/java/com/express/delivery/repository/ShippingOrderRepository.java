package com.express.delivery.repository;

import com.express.delivery.domain.Customer;
import com.express.delivery.domain.ShippingOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingOrderRepository extends CrudRepository<ShippingOrder, Long> {
    public List<ShippingOrder> findAll();

    public ShippingOrder findByCustomer(Customer customer);

    public ShippingOrder getById(Long id);
}