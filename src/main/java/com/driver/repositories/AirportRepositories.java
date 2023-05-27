package com.driver.repositories;

import com.driver.model.Airport;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class AirportRepositories {
    Map<String, Airport>airportDb=new HashMap<>();
    Map<Integer, Flight>flightDb=new HashMap<>();
    Map<Integer, Passenger>passengerDb=new HashMap<>();
    Map<Integer,List<Integer>>flightPassengerDb=new HashMap<>();
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

    public String bookATicket(int flightId,int passengerId){
        if(!flightPassengerDb.containsKey(flightId))
       flightPassengerDb.put(flightId,new ArrayList<>());
       int capacity=0;

               Flight flight=flightDb.get(flightId);
               capacity=flightDb.get(flightId).getMaxCapacity();

        int passengers=0;
       boolean check=false;

               passengers=flightPassengerDb.get(flightId).size();
               List<Integer>p=flightPassengerDb.get(flightId);
               for(int i:p) {
                   if (i == passengerId) check = true;
               }

       if(passengers>capacity || check==true)
           return "FAILURE";


               p.add(passengerId);
               flightPassengerDb.put(flightId,p);

       return "SUCCESS";
    }

     public int getNumberOfPeopleOn(int flightId){
        int people=0;
        for(int id:flightPassengerDb.keySet()){
            if(id==flightId){
                people=flightPassengerDb.get(id).size();
            }
        }
        return people;
     }

    public int calculateFlightFare(int flightId){
        int fare=0;
        for(int id:flightPassengerDb.keySet()){
            if(id==flightId){
                fare=3000+50*(flightPassengerDb.get(id).size());
            }
        }
        return fare;
    }

    public String cancelATicket(int flightId,int passengerId){
        boolean check=false;
      for(int id:flightPassengerDb.keySet()){
          if(id==flightId){
              List<Integer>passengers=flightPassengerDb.get(id);
              for(int p:passengers){
                  if(p==passengerId) {
                      passengers.remove(p);
                      check=true;
                  }
              }
              flightPassengerDb.put(id,passengers);
          }
      }
      if(check)return "SUCCESS";
      return "FAILURE";
    }

    public int countOfBookingsDoneByPassengerAllCombined(int passengerId){
        int count=0;
        for(List<Integer> p:flightPassengerDb.values()){
            for(int id:p){
               if(id==passengerId)
                   count++;
            }
        }
        return count;
    }

    public int calculateRevenueOfAFlight(int flightId){
        int people=0;
        for(int id:flightPassengerDb.keySet()){
            if(id==flightId)
                people=flightPassengerDb.get(id).size();
        }
        return people;
    }

}
