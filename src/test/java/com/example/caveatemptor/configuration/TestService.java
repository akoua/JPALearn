package com.example.caveatemptor.configuration;

import com.example.caveatemptor.repository.AddressRepository;
import com.example.caveatemptor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void storeLoadEntities() {

    }
}
