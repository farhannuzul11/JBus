package farhanNuzulNJBusAF;

public class Account extends Serializable{
    public String email;
    public String name;
    public String password;
 
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "id: " + this.id + " name: " + this.name + " email: " + this.email + " password: " + this.password;
    }
}
