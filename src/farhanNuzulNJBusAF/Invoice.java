package farhanNuzulNJBusAF;

import java.sql.Timestamp;

public class Invoice extends Serializable{
    public Timestamp time;
    public BusRating rating;
    public int buyerId;
    public int renterId;
    public PaymentStatus status;
    
    public enum BusRating{
        NONE, NEUTRAL, GOOD, BAD;
    }
    
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }
       
    public Invoice(int buyerId, int renterId){
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(Account buyer, Renter renter){
        this.time = new Timestamp(System.currentTimeMillis());       
    }
    
    public String toString(){
        return "id: " + this.id + " Time: " + this.time + " buyerID: "+ this.buyerId + " renterId: " + this.renterId;
    }
}
