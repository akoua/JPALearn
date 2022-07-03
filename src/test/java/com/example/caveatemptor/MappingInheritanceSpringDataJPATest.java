package com.example.caveatemptor;

import com.example.caveatemptor.configuration.SpringDataConfiguration;
import com.example.caveatemptor.entity.BankAccount;
import com.example.caveatemptor.entity.CreditCard;
import com.example.caveatemptor.entity.Item;
import com.example.caveatemptor.entity.others.Dimensions;
import com.example.caveatemptor.entity.others.MonetaryAmount;
import com.example.caveatemptor.entity.others.Weight;
import com.example.caveatemptor.repository.BankAccountRepository;
import com.example.caveatemptor.repository.BillingDetailsRepository;
import com.example.caveatemptor.repository.CreditCardRepository;
import com.example.caveatemptor.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Currency;
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
    @Autowired
    private ItemRepository itemRepository;

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

        Dimensions dimension = new Dimensions()
                .withDepth(BigDecimal.valueOf(1.0))
                .withHeight(BigDecimal.valueOf(20.0))
                .withWidth(BigDecimal.valueOf(30.0));
        dimension.setName("Centimètre");
        dimension.setSymbol("cm");
        Dimensions dimension2 = new Dimensions()
                .withDepth(BigDecimal.valueOf(1.0))
                .withHeight(BigDecimal.valueOf(20.0))
                .withWidth(BigDecimal.valueOf(30.0));
        dimension2.setName("Mètre");
        dimension2.setSymbol("m");
        Weight weight = new Weight()
                .withValue(BigDecimal.valueOf(40.0));
        weight.setName("Kilogramme");
        weight.setSymbol("Kg");

        Item item = new Item()
                .withName("Test ArtWork")
                .withBuyNowPrice(new MonetaryAmount(BigDecimal.valueOf(50_000.1), Currency.getInstance("USD")))
                .withInitialPrice(new MonetaryAmount(BigDecimal.valueOf(1_000.0), Currency.getInstance("USD")))
                .withDimensions(dimension)
                .withWeight(weight);
        Item item2 = new Item()
                .withName("Test 2")
                .withBuyNowPrice(new MonetaryAmount(BigDecimal.valueOf(5_000.1), Currency.getInstance("USD")))
                .withInitialPrice(new MonetaryAmount(BigDecimal.valueOf(5_000.0), Currency.getInstance("USD")))
                .withDimensions(dimension2)
                .withWeight(weight);
        itemRepository.save(item);
        itemRepository.save(item2);

        List items = itemRepository.findAll();
        List<Item> kilogramme = itemRepository.findByDimensions_SymbolIgnoreCase("cm");
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