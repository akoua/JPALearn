package com.example.caveatemptor.repository;

import com.example.caveatemptor.entity.CreditCard;

import java.util.List;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {

    List<CreditCard> findByExpYear(String expYear);
}
