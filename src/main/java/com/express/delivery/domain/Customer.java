package com.express.delivery.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
@Table(name = "customer")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer {
    @Id
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    private String firstname;
    private String lastname;

    @OneToOne
    @JoinColumn(name = "geo_location_id")
    private GeoLocation geoLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public GeoLocation getGeoLocation(){
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation){
        this.geoLocation = geoLocation;
    }


    public String getFullname(){
        return (this.firstname + " " +this.lastname);
    }
}


