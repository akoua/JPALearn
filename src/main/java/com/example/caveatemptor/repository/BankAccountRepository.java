package com.example.caveatemptor.repository;

import com.example.caveatemptor.entity.BankAccount;

import java.util.List;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {

    List<BankAccount> findBySwift(String swift);
}
