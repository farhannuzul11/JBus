package com.farhanNuzulNJBusAF.controller;

import com.farhanNuzulNJBusAF.Account;
import com.farhanNuzulNJBusAF.dbjson.JsonAutowired;
import com.farhanNuzulNJBusAF.dbjson.Serializable;
import com.farhanNuzulNJBusAF.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    public static @JsonAutowired(value = Account.class, filepath = "C:\\Users\\asus\\OneDrive\\Dokumen\\Semester 3\\OOP\\OOP 01\\JBus\\src\\main\\java\\com\\farhanNuzulNJBusAF\\json\\account.json");
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        return new Account(name, email, password);
    }

    @GetMapping("/{id}")
    String getById(@PathVariable int id) { return "account id " + id + " not found!"; }
}

