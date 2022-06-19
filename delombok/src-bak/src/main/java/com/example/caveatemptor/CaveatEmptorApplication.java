package com.example.caveatemptor;

import com.example.caveatemptor.entity.Bid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaveatEmptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaveatEmptorApplication.class, args);

        Bid bid = new Bid();
        bid.getAmount();
    }

}
