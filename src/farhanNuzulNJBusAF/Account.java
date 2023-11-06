package farhanNuzulNJBusAF;


import java.util.regex.Pattern;

public class Account extends Serializable{
    public String email;
    public String name;
    public String password;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
    public static final String REGEX_PW = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{8,}$";
 
    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        return "id: " + this.id + "\nname: " + this.name + "\nemail: " + this.email + "\npassword: " + this.password + "\n";
    }

    public boolean validate(){
        if(Pattern.matches(REGEX_EMAIL, email) && Pattern.matches(REGEX_PW, password)){
            return true;
        } return false;
    }
//    @Override
//    public Object write(){
//        return this;
//    }
//
//    @Override
//    public boolean read (String component){
//        return true;
//    }
}
