package com.farhanNuzulNJBusAF;

import com.farhanNuzulNJBusAF.dbjson.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Bus extends Serializable {
    public String name;
    public List<Facility> facility;
    public Price price;
    public int capacity;
    public BusType busType;
    public Station departure;
    public Station arrival;
    public List<Schedule> schedules;
    public int accountId;
    public Bus(String name, List<Facility> facility, Price price, int capacity, BusType busType, Station arrival, Station departure, int accountId) {
        this.name = name;
        this.facility = facility;
        this.price = price;
        this.capacity = capacity;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<>();
        this.accountId = accountId;
    }
    
    public String toString() {
        return "id: " + this.id + " name: " + this.name + " facility: " + this.facility + " price: " + this.price + " capacity: " + this.capacity + " busType: " + this.busType + " departure: " + this.departure + " arrival: " + this.arrival + "\n";
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
