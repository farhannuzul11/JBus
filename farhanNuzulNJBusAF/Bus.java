package farhanNuzulNJBusAF;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


public class Bus extends Serializable implements FileParser{
     public String name;
     public Facility facility;
     public Price price;
     public int capacity;
     public BusType busType;
     public City city;
     public Station departure;
     public Station arrival;
     public List<Schedule> schedule;
     
      public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
        super (id);
        this.name = name;
        this.facility = facility;
        this.capacity = capacity;
        this.price = price;
        this.busType = busType;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.schedule = new ArrayList();
    }
    
    public String toString (){
        return "id: " + this.id + " name: " + this.name + " facility: " + this.facility + " price: " + this.price + " capacity: " + this.capacity + " busType: " + this.busType + " city: " + this.city + " departure: " + this.departure + " arrival " + this.arrival; 
    }
    
    @Override
    public Object write(){
        return this;
    }
    
    @Override
    public boolean read (String component){
        return true;
    }
    
    public void addSchedule(Calendar calendar){
        Schedule schedule = new Schedule (calendar, this.capacity);
        this.schedule.add(schedule);
    }
    
    public void printSchedule (Schedule schedule){
        System.out.println("Tanggal Berangkat:" + schedule);
    }
}
