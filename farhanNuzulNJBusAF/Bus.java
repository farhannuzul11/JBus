package farhanNuzulNJBusAF;


public class Bus extends Serializable{
     public String name;
     public Facility facility;
     public Price price;
     public int capacity;
     
      public Bus(int id, String name, Facility facility, Price price, int capacity){
        super (id);
        this.name = name;
        this.facility = facility;
        this.capacity = capacity;
        this.price = price;
        
    }
}
