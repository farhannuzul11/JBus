package farhanNuzulNJBusAF;

import java.util.Calendar;

public class Invoice extends Serializable{
    public Calendar time;
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
       
    public Invoice(int id, int buyerId, int renterId){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = Calendar.getInstance();
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.time = Calendar.getInstance();       
    }
    
    public String toString(){
        return "id: " + this.id + " Time: " + this.time + " buyerID: "+ this.buyerId + " renterId: " + this.renterId;
    }
}
//masih kurang paham di sini
