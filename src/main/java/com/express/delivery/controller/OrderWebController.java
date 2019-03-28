package com.express.delivery.controller;

import com.express.delivery.domain.Depot;
import com.express.delivery.domain.GeoLocation;
import com.express.delivery.domain.ShippingOrder;
import com.express.delivery.domain.Store;
import com.express.delivery.repository.CustomerRepository;
import com.express.delivery.repository.ShippingOrderRepository;
import com.express.delivery.services.ShippingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        GeoLocation geoLocation = shippingOrder.getCustomer().getGeoLocation();
        Store closestStore = shippingOrderService.findClosestStoreByDeliveryAddress(geoLocation);
        Depot closestDepot = shippingOrderService.findClosestDepotByStoreAddress(closestStore.getGeoLocation());
        shippingOrder.setStore(closestStore);
        shippingOrder.setDepot(closestDepot);
        orderRepository.save(shippingOrder);
        return shippingOrder;
    }

    /*Double[] values = [nearStatement.lat as Double, nearStatement.lng as Double, nearStatement.lat as Double, nearStatement.radius as Double] as Double[]
                Type[] types = [Hibernate.DOUBLE, Hibernate.DOUBLE, Hibernate.DOUBLE, Hibernate.DOUBLE] as Type[]
                valueCriterion = Restrictions.sqlRestriction("( 6371* acos( cos( radians(?) ) * cos( radians( value_.geo_lat_value ) ) * cos( radians( value_.geo_lng_value ) - radians(?) ) + sin( radians(?) ) * sin( radians( value_.geo_lat_value ) ) ) ) < ? ", values, types)
*/
}
