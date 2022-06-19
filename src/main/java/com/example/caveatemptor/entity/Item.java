package com.example.caveatemptor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@With
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private UUID id;
    private String name;
    private String description;
    private Date createOn;
    private boolean verified;
    //    private AuctionType auctionType;
    private BigDecimal initialPrice;
    private Date auctionStart;
    private Date auctionEnd;
    @OneToMany(mappedBy = "item")
    private Set<Bid> bids;
}
