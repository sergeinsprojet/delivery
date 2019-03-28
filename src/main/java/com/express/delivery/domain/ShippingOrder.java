package com.express.delivery.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "shipping_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShippingOrder {
    static Integer DRONE_SPEED = 60;
    static Integer MILLISECONDS_IN_HOURS = 3600000;
    static Integer MILLISECONDS_IN_MINUTES = 600000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne
    @JoinColumn(name = "store_id")
    Store store;

    @OneToOne
    @JoinColumn(name = "depot_id")
    Depot depot;

    Date orderedDate = new Date();

    Date deliveredDate;

    public Long getId() {
        return this.id;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Store getStore(){
        return this.store;
    }

    public void setStore(Store store){
        this.store = store;
    }

    public Depot getDepot(){
        return this.depot;
    }

    public void setDepot(Depot depot){
        this.depot = depot;
    }

    public BigDecimal getShippingDistance(){
        Double distance = calculateDistanceFromA2B(depot.getGeoLocation(),store.getGeoLocation()) + calculateDistanceFromA2B(store.getGeoLocation(),customer.getGeoLocation());
        return new BigDecimal(distance).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Double calculateDistanceFromA2B(GeoLocation locationA, GeoLocation locationB){
        Double distance = ( 6371 * Math.acos( Math.cos( Math.toRadians(locationA.getGeoLat().doubleValue()) ) * Math.cos( Math.toRadians( locationB.getGeoLat().doubleValue() ) )
                * Math.cos( Math.toRadians( locationB.getGeoLng().doubleValue() ) - Math.toRadians(locationA.getGeoLng().doubleValue()) )
                + Math.sin( Math.toRadians(locationA.getGeoLat().doubleValue()) )
                * Math.sin( Math.toRadians( locationB.getGeoLat().doubleValue() ) ) ) );

        return distance;
    }

    public String getFormatedDeliveryTimeInMinutesAndSeconds(){
        return (new SimpleDateFormat("mm:ss")).format(new Date(getDeliveryTimeInMilliseconds()));
    }

    public Long getDeliveryTimeInMilliseconds(){
        return (long)(this.getShippingDistance().doubleValue()/DRONE_SPEED * MILLISECONDS_IN_HOURS);
    }
}
