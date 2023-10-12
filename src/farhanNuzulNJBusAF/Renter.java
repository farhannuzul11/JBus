package farhanNuzulNJBusAF;

public class Renter extends Serializable{
    public String companyName;
    public String address;
    public int phoneNumber;
    
    public Renter(String companyName){
        this.companyName = companyName;
    }
    
    public Renter(String companyName, String address){
        this.companyName = companyName;
        this.address = ""; //cek lagi
    }
    
    public Renter(String companyName, int phoneNumber){
        this.companyName = companyName;
        this.phoneNumber = 0;
    }
    
    public Renter(String companyName, int phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = 0;
        this.address = "";
    }

}
