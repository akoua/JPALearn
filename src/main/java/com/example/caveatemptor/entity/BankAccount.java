package com.example.caveatemptor.entity;

import com.example.caveatemptor.entity.others.BillingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id_bank_account"))
})
public class BankAccount extends BillingDetails {
    @NotNull
    private String owner;
    private String account;
    private String bankName;
    private String swift;
}
