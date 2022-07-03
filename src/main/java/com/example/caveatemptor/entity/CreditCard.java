package com.example.caveatemptor.entity;

import com.example.caveatemptor.entity.others.BillingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreditCard extends BillingDetails {
    private String cardNumber;
    private String expMonth;
    private String expYear;
}
