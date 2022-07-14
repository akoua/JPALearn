package com.example.caveatemptor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "foreignKeyGenerator")
    @GenericGenerator(
            name = "foreignKeyGenerator",
            strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "property", value = "userT"
            )
    )
    private String id;
    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "city")),
            @AttributeOverride(name = "country", column = @Column(name = "pays"))
    })
    private City city;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private User userT;
}