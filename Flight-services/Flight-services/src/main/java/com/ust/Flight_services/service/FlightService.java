package com.ust.Flight_services.service;

import com.ust.Flight_services.model.Flight;
import com.ust.Flight_services.repo.Flightrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private Flightrepo repo;
    public Flight addFlight(Flight flight) {
        return repo.save(flight);
    }

    public List<Flight> getFlightsByAirline(String airlinecode) {
        return repo.findByAirlinecode(airlinecode);
    }
}
