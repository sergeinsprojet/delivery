package com.express.delivery.controller;

import com.express.delivery.domain.Customer;
import com.express.delivery.domain.GeoLocation;
import com.express.delivery.repository.CustomerRepository;
import com.express.delivery.repository.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexWebController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GeoLocationRepository geoLocationRepository;


    @GetMapping("/")
    public String index(Model model){
        List<Customer> customerList = customerRepository.findAll();
        List<GeoLocation> geoLocationList = geoLocationRepository.findAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("geoLocationList", geoLocationList);
        return "shop";
    }

    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }
}
