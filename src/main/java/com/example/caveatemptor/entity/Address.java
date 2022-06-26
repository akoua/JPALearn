package com.example.caveatemptor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class Address implements Serializable {

    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "city")),
            @AttributeOverride(name = "country", column = @Column(name = "pays"))
    })
    private City city;
}