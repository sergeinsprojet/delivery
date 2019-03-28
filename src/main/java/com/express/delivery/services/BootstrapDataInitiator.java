package com.express.delivery.services;

import com.express.delivery.domain.Customer;
import com.express.delivery.domain.Depot;
import com.express.delivery.domain.GeoLocation;
import com.express.delivery.domain.Store;
import com.express.delivery.repository.CustomerRepository;
import com.express.delivery.repository.DepotRepository;
import com.express.delivery.repository.GeoLocationRepository;
import com.express.delivery.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class BootstrapDataInitiator implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(BootstrapDataInitiator.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GeoLocationRepository geoLocationRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private DepotRepository depotRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        log.info("Start bootstrapping");

        createInitialData();

        log.info("Finnished bootstrapping");
    }

    private void createInitialData() {
        log.info("... creating system user");

        ArrayList<GeoLocation> geolocationList = new ArrayList<>();
        GeoLocation depotGeolocation1 = new GeoLocation();
        depotGeolocation1.setId(Long.valueOf(1));
        depotGeolocation1.setCode("ChIJ09_IMWLJuEcRdsvnkJRfCsw");
        depotGeolocation1.setName("Metrostrasse 12, 40235 Düsseldorf");
        depotGeolocation1.setGeoLat(BigDecimal.valueOf(51.234672));
        depotGeolocation1.setGeoLng(BigDecimal.valueOf(6.825636));
        geolocationList.add(depotGeolocation1);

        GeoLocation depotGeolocation2 = new GeoLocation();
        depotGeolocation2.setId(Long.valueOf(2));
        depotGeolocation2.setCode("ChIJ09_IMWLJuEcRdsvnkJRfCsw");
        depotGeolocation2.setName("Am Albertussee 1, 40549 Düsseldorf");
        depotGeolocation2.setGeoLat(BigDecimal.valueOf(51.236485));
        depotGeolocation2.setGeoLng(BigDecimal.valueOf(6.723946));
        geolocationList.add(depotGeolocation2);

        GeoLocation customerGeolocation1 = new GeoLocation();
        customerGeolocation1.setId(Long.valueOf(3));
        customerGeolocation1.setCode("ChIJTSmgqmvKuEcRNPEHt3y1DUA");
        customerGeolocation1.setName("Am Albertussee 1, 40549 Düsseldorf");
        customerGeolocation1.setGeoLat(BigDecimal.valueOf(51.211716));
        customerGeolocation1.setGeoLng(BigDecimal.valueOf(6.770084));
        geolocationList.add(customerGeolocation1);

        GeoLocation customerGeolocation2 = new GeoLocation();
        customerGeolocation2.setId(Long.valueOf(4));
        customerGeolocation2.setCode("ChIJ8yBcV_XJuEcRayZhfVTKr_8");
        customerGeolocation2.setName("Kaiserstraße 2, 40479 Düsseldorf");
        customerGeolocation2.setGeoLat(BigDecimal.valueOf(51.23516));
        customerGeolocation2.setGeoLng(BigDecimal.valueOf(6.7784));
        geolocationList.add(customerGeolocation2);

        GeoLocation customerGeolocation3 = new GeoLocation();
        customerGeolocation3.setId(Long.valueOf(5));
        customerGeolocation3.setCode("EjBXaWxkZW5icnVjaHN0cmHDn2UgMiwgNDA1NDUgRMO8c3NlbGRvcmYsIEdlcm1hbnkiGhIYChQKEglZvkO3Bcq4RxEWYQezseyNiRAC");
        customerGeolocation3.setName("Wildenbruchstraße 2, 40545 Düsseldorf, Germany");
        customerGeolocation3.setGeoLat(BigDecimal.valueOf(51.2276));
        customerGeolocation3.setGeoLng(BigDecimal.valueOf(6.759938));
        geolocationList.add(customerGeolocation3);

        GeoLocation customerGeolocation4 = new GeoLocation();
        customerGeolocation4.setId(Long.valueOf(6));
        customerGeolocation4.setCode("ChIJE_w5YpTLuEcRIcngSiAKXwY");
        customerGeolocation4.setName("Schlesische Straße 5, 40231 Düsseldorf");
        customerGeolocation4.setGeoLat(BigDecimal.valueOf(51.208047));
        customerGeolocation4.setGeoLng(BigDecimal.valueOf(6.831133));
        geolocationList.add(customerGeolocation4);

        GeoLocation storeGeolocation1 = new GeoLocation();
        storeGeolocation1.setId(Long.valueOf(7));
        storeGeolocation1.setCode("ChIJb3DDsdi1uEcRhPX5ElDvk_s");
        storeGeolocation1.setName("Schiessstraße 31, 40549 Düsseldorf");
        storeGeolocation1.setGeoLat(BigDecimal.valueOf(51.237478));
        storeGeolocation1.setGeoLng(BigDecimal.valueOf(6.719531));
        geolocationList.add(storeGeolocation1);

        GeoLocation storeGeolocation2 = new GeoLocation();
        storeGeolocation2.setId(Long.valueOf(8));
        storeGeolocation2.setCode("ChIJN0a_ZEHKuEcRW00qXpQ-eXE");
        storeGeolocation2.setName("Friedrichstraße 152, 40217 Düsseldorf");
        storeGeolocation2.setGeoLat(BigDecimal.valueOf(51.209056));
        storeGeolocation2.setGeoLng(BigDecimal.valueOf(6.778578));
        geolocationList.add(storeGeolocation2);

        GeoLocation storeGeolocation3 = new GeoLocation();
        storeGeolocation3.setId(Long.valueOf(9));
        storeGeolocation3.setCode("ChIJzRDbB6K1uEcR5CQo_SxVbSY");
        storeGeolocation3.setName("Breslauer Str. 2, 41460 Neuss");
        storeGeolocation3.setGeoLat(BigDecimal.valueOf(51.201994));
        storeGeolocation3.setGeoLng(BigDecimal.valueOf(6.718629));
        geolocationList.add(storeGeolocation3);


        GeoLocation storeGeolocation4 = new GeoLocation();
        storeGeolocation4.setId(Long.valueOf(10));
        storeGeolocation4.setCode("ChIJAatyXiW0uEcRT0QMJcR6vpk");
        storeGeolocation4.setName("Bataverstraße 93, 41462 Neuss");
        storeGeolocation4.setGeoLat(BigDecimal.valueOf(51.231234));
        storeGeolocation4.setGeoLng(BigDecimal.valueOf(6.685699));
        geolocationList.add(storeGeolocation4);

        GeoLocation storeGeolocation5 = new GeoLocation();
        storeGeolocation5.setId(Long.valueOf(11));
        storeGeolocation5.setCode("ChIJAQOpA43IuEcR6wpbBWWkboU");
        storeGeolocation5.setName("Am Sandbach 30, 40878 Ratingen");
        storeGeolocation5.setGeoLat(BigDecimal.valueOf(51.297171));
        storeGeolocation5.setGeoLng(BigDecimal.valueOf(6.831176));
        geolocationList.add(storeGeolocation5);

        geoLocationRepository.saveAll(geolocationList);

        Customer customer1 = new Customer();
        customer1.setId(Long.valueOf(1));
        customer1.setFirstname("Peter");
        customer1.setLastname("Parker");
        customer1.setGeoLocation(customerGeolocation1);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(Long.valueOf(2));
        customer2.setFirstname("James");
        customer2.setLastname("Howlett");
        customer2.setGeoLocation(customerGeolocation2);
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setId(Long.valueOf(3));
        customer3.setFirstname("Scott");
        customer3.setLastname("Summers");
        customer3.setGeoLocation(customerGeolocation3);
        customerRepository.save(customer3);

        Customer customer4 = new Customer();
        customer4.setId(Long.valueOf(4));
        customer4.setFirstname("Matthew");
        customer4.setLastname("Murdock");
        customer4.setGeoLocation(customerGeolocation4);
        customerRepository.save(customer4);

        Store store1 = new Store();
        store1.setId(Long.valueOf(1));
        store1.setName("Store 1");
        store1.setGeoLocation(storeGeolocation1);
        storeRepository.save(store1);

        Store store2 = new Store();
        store2.setId(Long.valueOf(2));
        store2.setName("Store 2");
        store2.setGeoLocation(storeGeolocation2);
        storeRepository.save(store2);

        Store store3 = new Store();
        store3.setId(Long.valueOf(3));
        store3.setName("Store 3");
        store3.setGeoLocation(storeGeolocation3);
        storeRepository.save(store3);

        Depot depot1 = new Depot();
        depot1.setId(Long.valueOf(4));
        depot1.setName("Depot 1");
        depot1.setGeoLocation(depotGeolocation1);
        depotRepository.save(depot1);

        Depot depot2 = new Depot();
        depot2.setId(Long.valueOf(5));
        depot2.setName("Depot 2");
        depot2.setGeoLocation(depotGeolocation2);
        depotRepository.save(depot2);
    }
}