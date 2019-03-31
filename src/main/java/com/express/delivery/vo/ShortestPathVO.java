package com.express.delivery.vo;

import com.express.delivery.domain.Customer;
import com.express.delivery.domain.Depot;
import com.express.delivery.domain.Store;

public class ShortestPathVO {
    private Depot depot;
    private Store store;
    private Customer customer;

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
