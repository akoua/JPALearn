package com.example.caveatemptor.entity;

import com.example.caveatemptor.entity.converter.ZipConverter;
import com.example.caveatemptor.entity.others.ZipCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    @NotNull
    @Column(nullable = false, length = 5)
    @Convert(converter = ZipConverter.class)
    private ZipCode zipcode;

    @NotNull
    @Column(nullable = false)
    private String name;

    //    @NotNull
//    @Column(nullable = false)
    private String country;
}