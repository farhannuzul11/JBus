package com.farhanNuzulNJBusAF;

import java.util.regex.Pattern;

public class Renter extends Serializable{
    public String companyName;
    public String address;
    public String phoneNumber;

    private final String REGEX_NAME = "[A-Z][A-Za-z0-9_]{3,19}$";

    private final String REGEX_PHONE = "^[0-9]{9,12}$";


    
    public Renter(String companyName){
        this.companyName = companyName;
    }
    
//    public Renter(String companyName, String address){
//        this.companyName = companyName;
//        this.address = ""; //cek lagi
//    }
    
    public Renter(String companyName, String phoneNumber){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(String companyName, String phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean validate(){
        String phoneNumber_S = String.valueOf(phoneNumber);
        if(Pattern.matches(REGEX_NAME, companyName) && Pattern.matches(REGEX_PHONE, phoneNumber_S)){
            return true;
        }
        return false;
    }
}
