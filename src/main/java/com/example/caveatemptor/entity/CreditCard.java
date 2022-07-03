package com.example.caveatemptor.entity;

import com.example.caveatemptor.entity.others.BillingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "owner", column = @Column(name = "cc_owner", nullable = false, insertable = false, updatable = false))
public class CreditCard extends BillingDetails {
    @NotNull
    private String cc_owner;
    private String cardNumber;
    private String expMonth;
    private String expYear;
}
