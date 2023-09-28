package farhanNuzulNJBusAF;

public class Voucher extends Serializable{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(int id, String name, int code, Type type, double minimum, double cut){
        super(id);
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public boolean isUsed(){
        return this.used;
    }
    
    public boolean canApply(Price price){
        if (price.price > this.minimum && this.used == false){
            return true;
        }
        else{
            return false;
        }
    }
    
    public double apply(Price price){
        this.used = true;
        
        if(this.type == Type.DISCOUNT)
            return (price.price * ((100-this.cut)/100));
        else
            return price.price - cut;
    }
}