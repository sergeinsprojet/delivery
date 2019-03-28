package com.express.delivery.services;

import com.express.delivery.domain.ShippingOrder;

import java.util.List;

public interface IShippingOrderService {
    public List<ShippingOrder> findAll();
}
