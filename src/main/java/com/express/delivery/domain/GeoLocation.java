package com.express.delivery.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "geo_location")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GeoLocation {
    @Id
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    @Column(nullable = false)
    private String name;
    private String code;
    @Column(nullable = false, precision = 16, scale = 6)
    @Type(type = "big_decimal")
    private BigDecimal geoLat;
    @Column(nullable = false, precision = 16, scale = 6)
    @Type(type = "big_decimal")
    private BigDecimal geoLng;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(BigDecimal geoLat) {
        this.geoLat = geoLat;
    }

    public BigDecimal getGeoLng() {
        return geoLng;
    }

    public void setGeoLng(BigDecimal geoLng) {
        this.geoLng = geoLng;
    }



}