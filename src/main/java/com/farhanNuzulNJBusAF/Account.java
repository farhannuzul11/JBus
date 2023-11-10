package com.farhanNuzulNJBusAF;


import com.farhanNuzulNJBusAF.dbjson.Serializable;

import java.util.regex.Pattern;

public class Account extends Serializable {
    public String email;
    public String name;
    public String password;
    public Renter company;
    public double balance;
    public static final String REGEX_PASSWORD = "^(?=.[a-z])(?=.[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
 
    public Account(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null; //ragu
    }
    
    public String toString(){
        return "id: " + this.id + "\nname: " + this.name + "\nemail: " + this.email + "\npassword: " + this.password + "\n";
    }

    public boolean validate(){
        if(Pattern.matches(REGEX_EMAIL, email) && Pattern.matches(REGEX_PASSWORD, password)){
            return true;
        } return false;
    }

    public boolean topUp(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Top-up successful. New balance: " + this.balance);
            return true;
        } else {
            System.out.println("Invalid top-up amount. Please provide a positive value.");
            return false;
        }
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
