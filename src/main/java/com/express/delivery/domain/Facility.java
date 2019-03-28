package com.express.delivery.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "facility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Facility {
    @Id
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "geo_location_id")
    GeoLocation geoLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoLocation getGeoLocation(){
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation){
        this.geoLocation = geoLocation;
    }


}
