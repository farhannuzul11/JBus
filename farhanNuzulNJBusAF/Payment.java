package farhanNuzulNJBusAF;

public class Payment extends Invoice{
    private int busId;
    public String departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat){
        super (id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat){
        super (id, buyer, renter, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;        
    }
    
    public String print(){
        return "id: " + this.id + " Time: " + this.time + " buyerID: "+ this.buyerId + " renterId: " + this.renterId + " busId: " + this.busId + " departureDate: " + this.departureDate + " busSeat: " + this.busSeat;
    }
    
    public int getBusId(){
        return this.busId;
    }  
}
