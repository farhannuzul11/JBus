package farhanNuzulNJBusAF;


public class Bus{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    
    public Bus(String name, Facility facility, Price price, int capacity){
        this.name = name;
        this.facility = facility;
        this.capacity = capacity;
        this.price = price;
    } //setter setter
    
}
