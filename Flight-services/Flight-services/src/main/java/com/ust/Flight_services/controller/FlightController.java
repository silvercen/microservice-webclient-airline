package com.ust.Flight_services.controller;

import com.ust.Flight_services.model.Flight;
import com.ust.Flight_services.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/addflight")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }
    @GetMapping("{airlinecode}")
    public List<Flight> getFlightsByAirline(@PathVariable String airlinecode){
        return flightService.getFlightsByAirline(airlinecode);
    }

}
