package farhanNuzulNJBusAF;


public class Bus extends Serializable{
     public String name;
     public Facility facility;
     public Price price;
     public int capacity;
     public BusType busType;
     public City city;
     public Station departure;
     public Station arrival;
     
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
    }
    
    public String toString (){
        return "id: " + this.id + " name: " + this.name + " facility: " + this.facility + " price: " + this.price + " capacity: " + this.capacity + " busType: " + this.busType + " city: " + this.city + " departure: " + this.departure + " arrival " + this.arrival; 
    }
}
