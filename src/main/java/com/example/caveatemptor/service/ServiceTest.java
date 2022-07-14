package com.example.caveatemptor.service;

import com.example.caveatemptor.entity.Address;
import com.example.caveatemptor.entity.City;
import com.example.caveatemptor.entity.Item;
import com.example.caveatemptor.entity.User;
import com.example.caveatemptor.entity.others.MonetaryAmount;
import com.example.caveatemptor.entity.others.SwizzZipCode;
import com.example.caveatemptor.repository.ItemRepository;
import com.example.caveatemptor.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    public void addNewInstance() {
        Item item = new Item()
                .withName("Akoua ArtWork")
                .withBuyNowPrice(new MonetaryAmount(BigDecimal.valueOf(50_000.1), Currency.getInstance("USD")))
                .withInitialPrice(new MonetaryAmount(BigDecimal.valueOf(1_000.0), Currency.getInstance("USD")));
        itemRepository.save(item);
        User user = new User().withName("Akoua").withShippingAddress(
                new Address().withCity(
                        new City().withName("Baby").withCountry("CI").withZipcode(new SwizzZipCode("1234"))
                ));
        userRepository.save(user);
        userRepository.findAll().stream().forEach(System.out::println);
    }
}
