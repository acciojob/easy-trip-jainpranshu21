package com.driver.services;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.repositories.AirportRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

public class AirportService {

    AirportRepositories airportRepositories=new AirportRepositories();

    public String addAirport(Airport airport){
        return airportRepositories.addAirport(airport);
    }

    public String getLargestAirportName(){
        List<Airport> airports=airportRepositories.getLargestAirportName();
        List<String>name=new ArrayList<>();
        int terminals=0;
        for(Airport airport:airports){
            if(airport.getNoOfTerminals()>terminals){
                terminals=airport.getNoOfTerminals();
            }
        }
        for(Airport airport:airports){
            if(airport.getNoOfTerminals()==terminals){
                name.add(airport.getAirportName());
            }
        }
        if(name.size()==1)return name.get(0);
        Collections.sort(name);
        return name.get(0);
    }

    public String addFlight(Flight flight){
        return airportRepositories.addFlight(flight);
    }

    public String addPassenger(Passenger passenger){
        return airportRepositories.addPassenger(passenger);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity,City toCity){
        List<Flight>flights=airportRepositories.getShortestDurationOfPossibleBetweenTwoCities();
        double duration=Double.MAX_VALUE;
        for (Flight flight:flights){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                if(flight.getDuration()<duration)
                    duration=flight.getDuration();
            }
        }
        if(duration!=Double.MAX_VALUE)return duration;
        return -1;
    }


    public int getNumberOfPeopleOn(Date date,String airportName) {
        int noOfPassengers=0;
        List<Airport>airports=airportRepositories.getLargestAirportName();
        City city=City.BANGLORE;
        for(Airport airport:airports){
            if(airport.getAirportName().equals(airportName))
                city=airport.getCity();

        }
        List<Flight>flights=airportRepositories.getShortestDurationOfPossibleBetweenTwoCities();
        for(Flight flight:flights){
            if(flight.getFlightDate()==date){
                if(flight.getToCity().equals(city) || flight.getFromCity().equals(city)){
                    noOfPassengers+=flight.getMaxCapacity();
                }
            }
        }
        return noOfPassengers;
    }
}
