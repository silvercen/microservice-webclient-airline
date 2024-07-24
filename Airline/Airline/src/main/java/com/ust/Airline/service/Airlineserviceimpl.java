package com.ust.Airline.service;

import com.ust.Airline.dto.Airlinedto;
import com.ust.Airline.dto.Flightdto;
import com.ust.Airline.dto.ResponseDto;
import com.ust.Airline.model.Airline;
import com.ust.Airline.repo.Airlinerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class Airlineserviceimpl  implements AirlineService{
    @Autowired
    private Airlinerepo repo;
    @Autowired
    private WebClient webClient;
    @Override
    public Airline addAirline(Airline airline) {
        return repo.save(airline);
    }

    @Override
    public ResponseDto getAirline(String airlinecode) {
        ResponseDto responseDto = new ResponseDto();
        Airline airline = repo.findByAirlinecode(airlinecode).orElseThrow(() -> new RuntimeException("Airline not found"));
        Airlinedto airlineDto = mapToAirline(airline);

        List<Flightdto> flightdtoList = webClient.get()
                .uri("http://localhost:9098/flights/" + airline.getAirlinecode())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Flightdto>>() {}).block();

        responseDto.setAirline(airlineDto);
        responseDto.setFlight(flightdtoList);

        return responseDto;
    }

    private Airlinedto mapToAirline(Airline airline) {
        Airlinedto dto=new Airlinedto();
        dto.setAirlinename(airline.getAirlinename());
        dto.setAirlinecode(airline.getAirlinecode());
        dto.setCountry(airline.getCountry());
        dto.setHeadquarters(airline.getHeadquarters());
        dto.setCeo(airline.getCeo());
        dto.setFoundedYear(airline.getFoundedYear());
        dto.setHubAirport(airline.getHubAirport());
        dto.setWebsite(airline.getWebsite());
        dto.setFleetSize(airline.getFleetSize());
        return dto;
    }
}
