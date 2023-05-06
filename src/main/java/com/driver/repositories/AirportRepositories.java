package com.driver.repositories;

import com.driver.model.Airport;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportRepositories {
    Map<String, Airport>airportDb=new HashMap<>();
    Map<Integer, Flight>flightDb=new HashMap<>();
    Map<Integer, Passenger>passengerDb=new HashMap<>();
    public String addAirport(Airport airport){
        String name=airport.getAirportName();
        airportDb.put(name,airport);
        return "SUCCESS";
    }

    public List<Airport> getLargestAirportName(){
        List<Airport>airports=new ArrayList<>();
        for (String name:airportDb.keySet()){
            airports.add(airportDb.get(name));
        }
        return airports;
    }

    public String addFlight(Flight flight){
        int id=flight.getFlightId();
        flightDb.put(id,flight);
        return "SUCCESS";
    }

    public String addPassenger(Passenger passenger){
        int passengerId=passenger.getPassengerId();
        passengerDb.put(passengerId,passenger);
        return "SUCCESS";
    }

    public List<Flight> getShortestDurationOfPossibleBetweenTwoCities(){
        List<Flight>flights=new ArrayList<>();
        for(int id:flightDb.keySet()){
            flights.add(flightDb.get(id));
        }
        return flights;
    }
}
