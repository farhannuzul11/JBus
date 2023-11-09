package com.farhanNuzulNJBusAF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;

public class Bus extends Serializable{
    public String name;
    public Facility facility;
    public Price price;
    public int capacity;
    public BusType busType;
    public City city;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;

    public Bus(String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
    }
    
    public String toString() {
        return "id: " + this.id + " name: " + this.name + " facility: " + this.facility + " price: " + this.price + " capacity: " + this.capacity + " busType: " + this.busType + " city: " + this.city + " departure: " + this.departure + " arrival: " + this.arrival + "\n";
    }

//    @Override
//    public Object write() {
//        return this;
//    }
//
//    @Override
//    public boolean read(String component) {
//        return true;
//    }

//    public void addSchedule(Timestamp schedule) {
//        schedules.add(new Schedule(schedule, this.capacity));
//    }

    public void addSchedule(Timestamp schedule) {
        try {
            for (Schedule existingSchedule : schedules) {
                if (existingSchedule.departureSchedule.equals(schedule)) {
                    System.out.println("Ada jadwal dengan waktu yang sama");
                    return;
                }
            }
            schedules.add(new Schedule(schedule, this.capacity));
        } catch (Exception e) {
            System.out.println("Kesalahan saat menambahkan jadwal: " + e.getMessage());
        }
    }

}
