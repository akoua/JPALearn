package com.example.caveatemptor.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bid {
    private BigDecimal amount;
    private Date createOn;
    private Item item;
}
