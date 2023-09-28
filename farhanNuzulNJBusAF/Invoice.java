package farhanNuzulNJBusAF;

public class Invoice extends Serializable{
    public String time;
    public int buyerId;
    public int renterId;
    
    public Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.time = time;        
    }
    
    public String print (){
        return "id: " + this.id + " Time: " + this.time + " buyerID: "+ this.buyerId + " renterId: " + this.renterId;
    }
}
