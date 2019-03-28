package com.express.delivery.repository;

import com.express.delivery.domain.GeoLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoLocationRepository extends CrudRepository<GeoLocation, Long> {
    public List<GeoLocation> findAll();
}

