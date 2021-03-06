package com.example.caveatemptor.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
public class Item {

    private String name;
    private String description;
    private Date createOn;
    private boolean verified;
//    private AuctionType auctionType;
    private BigDecimal initialPrice;
    private Date auctionStart;
    private Date auctionEnd;

    private Set<Bid> bids;
}
