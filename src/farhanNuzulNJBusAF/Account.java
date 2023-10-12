package farhanNuzulNJBusAF;


public class Account extends Serializable implements FileParser{
    public String email;
    public String name;
    public String password;
 
    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "id: " + this.id + " name: " + this.name + " email: " + this.email + " password: " + this.password;
    }
    
    @Override
    public Object write(){
        return this;
    }
    
    @Override
    public boolean read (String component){
        return true;
    }
}
