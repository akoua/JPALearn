package com.example.caveatemptor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private String id;
    private String name;
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.REMOVE)
    @JoinColumn(unique = true)
    private Address shippingAddress;
}
