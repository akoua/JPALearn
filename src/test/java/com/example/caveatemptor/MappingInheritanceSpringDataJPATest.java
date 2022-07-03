package com.example.caveatemptor;

import com.example.caveatemptor.configuration.SpringDataConfiguration;
import com.example.caveatemptor.entity.BankAccount;
import com.example.caveatemptor.entity.CreditCard;
import com.example.caveatemptor.repository.BankAccountRepository;
import com.example.caveatemptor.repository.BillingDetailsRepository;
import com.example.caveatemptor.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//On ajoute toutes les extensions que Spring utilise nativement pour les tests
//tels que JUnit%
@ExtendWith(SpringExtension.class)
//Le contexte du test est defini par les beans instanciés dans la classe
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJPATest {

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    void storeLoadEntities() {

        BankAccount bankAccount = new BankAccount()
                .withAccount("12345")
                .withBankName("Delta Bank")
                .withSwift("BANKXY12");
        bankAccount.setOwner("Mike Johnson");
        bankAccountRepository.save(bankAccount);

        CreditCard creditCard = new CreditCard()
                .withCardNumber("123456789")
                .withExpMonth("10")
                .withExpYear("2030");
        creditCard.setOwner("John Smith");
        creditCardRepository.save(creditCard);


        List all = billingDetailsRepository.findAll();
        List<CreditCard> creditCards =
                creditCardRepository.findByOwner("John Smith");
        List<BankAccount> bankAccounts =
                bankAccountRepository.findByOwner("Mike Johnson");
        List<CreditCard> creditCards2 =
                creditCardRepository.findByExpYear("2030");
        List<BankAccount> bankAccounts2 =
                bankAccountRepository.findBySwift("BANKXY12");

        assertAll(
                () -> assertEquals(1, creditCards.size()),
                () -> assertEquals("123456789",
                        creditCards.get(0).getCardNumber()),
                () -> assertEquals(1, bankAccounts.size()),
                () -> assertEquals("12345",
                        bankAccounts.get(0).getAccount()),
                () -> assertEquals(1, creditCards2.size()),
                () -> assertEquals("John Smith",
                        creditCards2.get(0).getOwner()),
                () -> assertEquals(1, bankAccounts2.size()),
                () -> assertEquals("Mike Johnson",
                        bankAccounts2.get(0).getOwner())
        );

    }
}