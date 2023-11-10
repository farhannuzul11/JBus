package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.Account;
import com.farhanNuzulNJBusAF.Algorithm;
import com.farhanNuzulNJBusAF.Renter;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.Serializable;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    public static @JsonAutowired(value = Account.class, filepath = "C:\\Users\\asus\\OneDrive\\Dokumen\\Semester 3\\OOP\\OOP 01\\JBus\\src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\account.json") JsonTable<Account> accountTable;
    public JsonTable<Account> getJsonTable(){
        return this.accountTable;
    }
    @PostMapping("/register")
    protected BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        Account acc = new Account(name, email, password);
        if (name.isBlank()){
            return new BaseResponse<>(false, "Gagal register", null);
        }
        if (!acc.validate()) {
            return new BaseResponse<>(false, "Gagal register", null);
        }
        if (Algorithm.exists(getJsonTable(), acc)) {
            return new BaseResponse<>(false, "Gagal register: Email sudah terdaftar", null);
        }

        String passwordToHash = password;
        String generatedPassword = null;
        try{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        Account new_acc = new Account(name, email, password);
        return new BaseResponse<>(true, "Berhasil register", new_acc);
    }
    @PostMapping("/login")
    protected BaseResponse<Account> login(
            @RequestParam String email,
            @RequestParam String password
    ){

        String generatedPassword = null;
        String foundedName = null;
        String foundedEmail = null;
        String foundedPassword = null;

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        for (Account a : getJsonTable()){
            if(a.email.equals(email) && a.password.equals(generatedPassword)){
                foundedName = a.name;
                foundedEmail = a.email;
                foundedPassword = a.password;
            }
        }
        Account acc = new Account(foundedName, foundedEmail, foundedPassword);
        if(foundedName.equals(null) && foundedEmail.equals(null) && foundedPassword.equals(null)){
            return new BaseResponse<>(false, "Gagal login", acc);
        } else{
            return new BaseResponse<>(true, "Berhasil login", acc);
        }
    }
    @PostMapping("/{id}/registerRenter")
    protected BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ){
        boolean status = false;
        for(Account a : getJsonTable()){
            if(a.company == null && a.id == id){
                status = true;
                a.company = new Renter(companyName);
            }
        }

        Renter renter = new Renter(companyName);
        if(status){
            return new BaseResponse<>(true, "Berhasil login", renter);
        } else {
            return new BaseResponse<>(false, "Gagal login", renter);
        }
    }

    @PostMapping("/{id}/topUp")
    protected BaseResponse<Double>topUp(@PathVariable int id, @RequestParam double amount){
        for (Account acc : accountTable){
            if (acc.id == id){
                if(acc.topUp(amount)){
                    return new BaseResponse<>(true, "Berhasil top up", amount);
                }
            }
        }
        return new BaseResponse<>(false, "Gagal top up", null);
    }




//    @GetMapping
//    String index() { return "account page"; }
//
//    @PostMapping("/register")
//    Account register
//            (
//                    @RequestParam String name,
//                    @RequestParam String email,
//                    @RequestParam String password
//            )
//    {
//        return new Account(name, email, password);
//    }
//
//    @GetMapping("/{id}")
//    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}

