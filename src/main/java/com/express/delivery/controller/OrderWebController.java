package com.express.delivery.controller;

import com.express.delivery.domain.Depot;
import com.express.delivery.domain.GeoLocation;
import com.express.delivery.domain.ShippingOrder;
import com.express.delivery.domain.Store;
import com.express.delivery.repository.CustomerRepository;
import com.express.delivery.repository.ShippingOrderRepository;
import com.express.delivery.services.ShippingOrderService;
import com.express.delivery.vo.ShortestPathVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class OrderWebController {
    @Autowired
    private ShippingOrderRepository orderRepository;
    @Autowired
    private ShippingOrderService shippingOrderService;


    @RequestMapping("/api/1.0/order/list")
    public List<ShippingOrder> listApi() {
        List<ShippingOrder> shippingOrderList = orderRepository.findAll();

        return shippingOrderList;
    }

    @RequestMapping("/api/1.0/order/save")
    public ShippingOrder saveApi(@RequestBody ShippingOrder shippingOrder) {
        ShortestPathVO shortestPathVO =  shippingOrderService.findShortestPath(shippingOrder.getCustomer());
        shippingOrder.setStore(shortestPathVO.getStore());
        shippingOrder.setDepot(shortestPathVO.getDepot());
        orderRepository.save(shippingOrder);
        return shippingOrder;
    }
}
