package com.example.Tride.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Document("rides")
public record Rides(
       int seats,
        int price,
                      String From,
                      String To,
                      String time,
                        List<String> passengers,
                      String emailid,int originalPrice,
       int originalSeats,String rideid

                      )
{

    public Rides addPassenger(String passenger) {
        List<String> updatedPassengers = this.passengers != null ? new ArrayList<>(this.passengers) : new ArrayList<>();
        updatedPassengers.add(passenger);
        int no_of_passangers =  this.passengers != null ? this.passengers.size() : 0;
        System.out.println(this.passengers.size());
        return new Rides(this.seats-1, this.price/no_of_passangers, this.From, this.To, this.time, updatedPassengers, this.emailid,this.originalPrice,this.originalSeats,this.rideid);

    }
    public Rides addPassengerdefault(String passenger) {
        List<String> updatedPassengers = this.passengers != null ? new ArrayList<>(this.passengers) : new ArrayList<>();
        updatedPassengers.add(passenger);
//        int no_of_passangers =  this.passengers != null ? this.passengers.size() : 0;
        return new Rides(this.seats-1, this.price/1, this.From, this.To, this.time, updatedPassengers, this.emailid,this.originalPrice,this.originalSeats,this.rideid);

    }
    public  Rides removePassenger(String passenger){
        List<String> updatedpassenger = this.passengers();
        updatedpassenger.remove(passenger);
        int no_of_passangers =  this.passengers != null ? this.passengers.size() : 0;
        return new Rides(this.seats+1, this.price/no_of_passangers, this.From, this.To, this.time,updatedpassenger, this.emailid,this.originalPrice,this.originalSeats,this.rideid);

    }
}
