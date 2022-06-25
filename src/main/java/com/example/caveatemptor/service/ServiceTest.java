package com.example.caveatemptor.service;

import com.example.caveatemptor.entity.Address;
import com.example.caveatemptor.entity.Item;
import com.example.caveatemptor.entity.User;
import com.example.caveatemptor.repository.ItemRepository;
import com.example.caveatemptor.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .withName("Akoua ArtWork");
        itemRepository.save(item);

        userRepository.save(new User().withName("Akoua").withAdress(new Address().withCity("Baby")));
    }
}
