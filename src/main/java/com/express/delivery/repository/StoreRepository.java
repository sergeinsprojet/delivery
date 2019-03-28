package com.express.delivery.repository;

import com.express.delivery.domain.GeoLocation;
import com.express.delivery.domain.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface StoreRepository extends CrudRepository<Store, Long> {
    public List<Store> findAll();

    public Store getById(Long id);

    @Query(value = "SELECT *, " +
            " ( 6371 * acos( cos( radians(?2) )"+
            " * cos( radians( gl.geo_lat ) )"+
            " * cos( radians( gl.geo_lng ) - radians(?1) ) + sin( radians(?2) )"+
            " * sin( radians( gl.geo_lat ) ) ) )"+
            " AS shortest_distance "+
            " FROM store as s"+
            " JOIN geo_location AS gl ON s.geo_location_id=gl.id "+
            " ORDER BY shortest_distance LIMIT 1",nativeQuery = true)
    Store findClosestStoreByLngAndLat(BigDecimal geoLng, BigDecimal geoLat);
}
