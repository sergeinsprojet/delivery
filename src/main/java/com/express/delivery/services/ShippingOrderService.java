package com.express.delivery.services;

import com.express.delivery.domain.Depot;
import com.express.delivery.domain.GeoLocation;
import com.express.delivery.domain.ShippingOrder;
import com.express.delivery.domain.Store;
import com.express.delivery.repository.CustomerRepository;
import com.express.delivery.repository.ShippingOrderRepository;
import com.express.delivery.repository.StoreRepository;
import com.express.delivery.repository.DepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingOrderService implements IShippingOrderService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShippingOrderRepository orderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Override
    public List<ShippingOrder> findAll() {
        return (List<ShippingOrder>) orderRepository.findAll();
    }

    public Store findClosestStoreByDeliveryAddress(GeoLocation geolocation){

        Store store = storeRepository.findClosestStoreByLngAndLat(geolocation.getGeoLng(), geolocation.getGeoLat());
        return store;
    }

    public Depot findClosestDepotByStoreAddress(GeoLocation geolocation){
        Depot depot = depotRepository.findClosestDepotByLngAndLat(geolocation.getGeoLng(), geolocation.getGeoLat());
        return depot;
    }

}
