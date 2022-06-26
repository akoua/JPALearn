package com.example.caveatemptor.entity;

import com.example.caveatemptor.entity.others.MonetaryAmount;
import com.example.caveatemptor.enums.AuctionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@With
@Entity
//@Immutable
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private String id;
    private String name;
    private String description;
    private Date createOn;
    private boolean verified;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType = AuctionType.HIGHEST_BID;
    @NotNull
    @Type(type = "monetary_amount_eur")
    @Columns(columns = {
            @Column(name = "initial_amount"),
            @Column(name = "initial_currency", length = 3)
    })
    private MonetaryAmount initialPrice;
    private Date auctionStart;
    private Date auctionEnd;
    @OneToMany(mappedBy = "item")
    private Set<Bid> bids;
    @NotNull
    @Type(type = "monetary_amount_usd")
    @Columns(columns = {
            @Column(name = "buynowprice_amount"),
            @Column(name = "buynowprice_currency", length = 3)
    })
    private MonetaryAmount buyNowPrice;

    /*@Id
    @GeneratedValue(generator = "ID_GENERATOR")
    public String getId() {
        return id;
    }

    public String getName() {
        return "custom name:" + name;
    }*/
}
