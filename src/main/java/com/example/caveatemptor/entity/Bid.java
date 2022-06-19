package com.example.caveatemptor.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Bid implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private UUID id;
    @Column
    private BigDecimal amount;
    @Column
    private Date createOn;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
