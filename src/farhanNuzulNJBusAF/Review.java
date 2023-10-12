package farhanNuzulNJBusAF;


public class Review extends Serializable{
    public String date;
    public String desc;
    
    public Review (String date, String desc){
        this.date = date;
        this.desc = desc;
    }
    
    public String toString(){
        return "id: " + this.id + " date: " + this.date + " desc: " + this.desc; 
    }
}
