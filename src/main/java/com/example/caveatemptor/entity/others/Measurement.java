package com.example.caveatemptor.entity.others;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data
public abstract class Measurement {
    @NotNull
    private String name;
    @NotNull
    private String symbol;
}