package farhanNuzulNJBusAF;

public class Station extends Serializable{
    public City city;
    public String stationName;
    public String address;
    
    public Station(int id, String stationName, City city, String address){
        super(id);
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }

    public String toString(){
        return "id: " + this.id + " stationName: " + this.stationName + " city: " + this.city + " address: " + this.address;
    }
}
