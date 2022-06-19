package com.example.caveatemptor;

import com.example.caveatemptor.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaveatEmptorApplication implements CommandLineRunner {

    @Autowired
    private ServiceTest serviceTest;

    public static void main(String[] args) {
        SpringApplication.run(CaveatEmptorApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        serviceTest.addNewInstance();
    }
}
