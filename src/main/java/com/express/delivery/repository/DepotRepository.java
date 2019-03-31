package com.express.delivery.repository;

import com.express.delivery.domain.Depot;
import com.express.delivery.domain.GeoLocation;
import com.express.delivery.domain.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepotRepository extends CrudRepository<Depot, Long> {
    public List<Depot> findAll();

    public Depot getById(Long id);

    @Query(value = "SELECT *, " +
            " ( 6371 * acos( cos( radians(?2) )"+
            " * cos( radians( gl.geo_lat ) )"+
            " * cos( radians( gl.geo_lng ) - radians(?1) ) + sin( radians(?2) )"+
            " * sin( radians( gl.geo_lat ) ) ) )"+
            " AS shortest_distance "+
            " FROM facility as s"+
            " JOIN geo_location AS gl ON s.geo_location_id=gl.id "+
            " WHERE s.facility_type='DEPOT'"+
            " ORDER BY shortest_distance LIMIT 1",nativeQuery = true)
    Depot findClosestDepotByLngAndLat(BigDecimal geoLng, BigDecimal geoLat);

    public Depot findByGeoLocation(GeoLocation geoLocation);
}
