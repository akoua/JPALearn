package com.example.caveatemptor.entity;

import com.example.caveatemptor.enums.AuctionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
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
    @Column(insertable = true)
    @ColumnDefault(value = "1.0")
    @Generated(value = GenerationTime.INSERT)
    private BigDecimal initialPrice;
    private Date auctionStart;
    private Date auctionEnd;
    @OneToMany(mappedBy = "item")
    private Set<Bid> bids;

    /*@Id
    @GeneratedValue(generator = "ID_GENERATOR")
    public String getId() {
        return id;
    }

    public String getName() {
        return "custom name:" + name;
    }*/
}
