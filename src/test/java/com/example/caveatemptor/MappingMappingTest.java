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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    @Transactional
    void storeLoadEntities() {

        User john = new User().withName("John Smith");
        User akoua = new User().withName("Akoua Yao");

        Address address = new Address()
                .withCity(
                        new City().withName("Baby").withCountry("CI").withZipcode(new SwizzZipCode("1234"))
                );

        addressRepository.save(address);
        john.setShippingAddress(address);
        akoua.setShippingAddress(address);

        userRepository.save(john);
//        userRepository.save(akoua); // This will be throw exception

        User user = userRepository.findById(john.getId()).get();
//        User user2 = userRepository.findById(akoua.getId()).get();
        Address address2 = addressRepository.findById(address.getId()).get();

        assertAll(
                () -> assertEquals("Baby", user.getShippingAddress().getCity().getName())
        );
        userRepository.delete(john);
        Optional<User> userById = userRepository.findById(john.getId());
        Optional<Address> addressById = addressRepository.findById(address.getId());
        assertAll(
                () -> assertFalse(userById.isPresent()),
                () -> assertFalse(addressById.isPresent())
        );

    }
}