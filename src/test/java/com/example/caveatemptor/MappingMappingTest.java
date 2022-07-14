package com.example.caveatemptor;

import com.example.caveatemptor.configuration.SpringDataConfiguration;
import com.example.caveatemptor.entity.Address;
import com.example.caveatemptor.entity.City;
import com.example.caveatemptor.entity.User;
import com.example.caveatemptor.entity.others.SwizzZipCode;
import com.example.caveatemptor.repository.AddressRepository;
import com.example.caveatemptor.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//On ajoute toutes les extensions que Spring utilise nativement pour les tests
//tels que JUnit%
@ExtendWith(SpringExtension.class)
//Le contexte du test est defini par les beans instanciés dans la classe
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingMappingTest {

//    @Autowired
//    private TestService testService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void storeLoadEntities() {

        User john = new User().withName("John Smith");

        Address address = new Address()
                .withUserT(john)
                .withCity(
                        new City().withName("Baby").withCountry("CI").withZipcode(new SwizzZipCode("1234"))
                );

        john.setShippingAddress(address);

        userRepository.save(john);

        User user = userRepository.findById(john.getId()).get();
        Address address2 = addressRepository.findById(address.getId()).get();

        assertAll(
                () -> assertEquals("Baby", user.getShippingAddress().getCity().getName()),
                () -> assertEquals("CI", address2.getUserT().getShippingAddress().getCity().getCountry()),
                () -> assertEquals(user.getId(), address2.getId()),
                () -> assertEquals(user.getName(), address2.getUserT().getName())
        );

    }
}