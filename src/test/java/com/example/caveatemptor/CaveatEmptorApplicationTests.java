package com.example.caveatemptor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
class CaveatEmptorApplicationTests {

    @Test
    void contextLoads() {
        PageRequest.of(1, 4, Sort.by("property"));
    }

}
