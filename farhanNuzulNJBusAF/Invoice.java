package farhanNuzulNJBusAF;

public class Invoice extends Serializable{
    public String time;
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
       
    public Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.time = time;        
    }
    
    public String toString(){
        return "id: " + this.id + " Time: " + this.time + " buyerID: "+ this.buyerId + " renterId: " + this.renterId;
    }
}
