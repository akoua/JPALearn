package com.example.caveatemptor.entity;

import com.example.caveatemptor.enums.ShipmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private String id;
    private ShipmentState shipmentState;
    @OneToOne(optional = true)
    private Address shipAddress;
}
