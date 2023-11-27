package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.Account;
import com.farhanNuzulNJBusAF.Algorithm;
import com.farhanNuzulNJBusAF.Predicate;
import com.farhanNuzulNJBusAF.Renter;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.Serializable;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    public static @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\account.json") JsonTable<Account> accountTable;
    public JsonTable<Account> getJsonTable(){
        return this.accountTable;
    }
    @PostMapping("/register")
    protected BaseResponse<Account> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ){
        Predicate<Account> s = (val) -> val.email.equals(email); //Account

        String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$";
        String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherEmail = patternEmail.matcher(email);

        if (name.isBlank() == false && matcherPassword.find() && matcherEmail.find() && Algorithm.exists(accountTable,s) == false) {
            String passwordToHash = password;
            String generatedPassword = null;

            try
            {
                MessageDigest md = MessageDigest.getInstance("MD5");

                md.update(passwordToHash.getBytes());

                byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                for (int i = 0;i < bytes.length;i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Account tmp =  new Account(name, email, generatedPassword);
            accountTable.addElement(tmp);
            return new BaseResponse<>(true, "Berhasil register", tmp);
        }
        return new BaseResponse<>(false, "Gagal register", null);

    }

    @PostMapping("/login")
    BaseResponse<Account> login(@RequestParam String email, @RequestParam String password){
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

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for (Account t : accountTable){
            if (t.email.equals(email) && t.password.equals(generatedPassword)){
                return new BaseResponse<>(true, "Berhasil login", t);
            }
        }
        return new BaseResponse<>(false, "Gagal login", null);

    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(@PathVariable int id, @RequestParam String companyName, @RequestParam String address, @RequestParam String phoneNumber){
        for (Account t : accountTable){
            if (t.id == id && t.company == null) {
                t.company = new Renter(companyName, phoneNumber, address);
                return new BaseResponse<>(true, "Berhasil register renter", t.company);
            }
        }
        return new BaseResponse<>(false, "Gagal register renter", null);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(@PathVariable int id, @RequestParam double amount){
        for (Account t : accountTable){
            if (t.id == id){
                if (t.topUp(amount)){
                    return new BaseResponse<>(true, "Top up berhasil dilakukan", amount);
                }
            }
        }
        return new BaseResponse<>(false, "Gagal melakukan top up ", null);
    }
}
