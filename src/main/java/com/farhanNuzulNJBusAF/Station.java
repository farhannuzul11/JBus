package com.farhanNuzulNJBusAF;

import com.farhanNuzulNJBusAF.dbjson.Serializable;

public class Station extends Serializable {
    public City city;
    public String stationName;
    public String address;
    
    public Station(String stationName, City city, String address){
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    public String toString(){
        return "id: " + this.id + " stationName: " + this.stationName + " city: " + this.city + " address: " + this.address;
    }
}
