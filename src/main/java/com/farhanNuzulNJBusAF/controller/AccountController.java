package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.*;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.Serializable;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller class for managing user accounts and related operations.
 *
 * This class handles user registration, login, renter registration, and top-up operations.
 * It uses a JSON-based table to store account information.
 *
 * @author Farhan Nuzul
 * @version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    /**
     * The JSON table to store account information.
     */
    public static @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\account.json") JsonTable<Account> accountTable;

    /**
     * Gets the JSON table instance.
     *
     * @return The JSON table instance for accounts.
     */
    public JsonTable<Account> getJsonTable(){
        return this.accountTable;
    }

    /**
     * Registers a new user account.
     *
     * @param name The name of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A BaseResponse containing the result of the registration and the created account.
     */
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

    /**
     * Logs in a user with the provided email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return A BaseResponse containing the result of the login and the logged-in account.
     */
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

    /**
     * Registers a renter for a specific user account.
     *
     * @param id The ID of the user account.
     * @param companyName The name of the renter company.
     * @param address The address of the renter company.
     * @param phoneNumber The phone number of the renter company.
     * @return A BaseResponse containing the result of the renter registration and the created renter.
     */
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

    /**
     * Tops up the account balance for a specific user account.
     *
     * @param id The ID of the user account.
     * @param amount The amount to top up.
     * @return A BaseResponse containing the result of the top-up operation and the updated account balance.
     */
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
