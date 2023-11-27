package com.farhanNuzulNJBusAF;

import com.farhanNuzulNJBusAF.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

@SpringBootApplication
public class JBus{
    public static void main (String[] args){
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }
}