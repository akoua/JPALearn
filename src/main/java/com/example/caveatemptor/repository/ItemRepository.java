package com.example.caveatemptor.repository;

import com.example.caveatemptor.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAll(Pageable pageable);

    List<Item> findByDimensions_SymbolIgnoreCase(String name);

    List<Item> findByWeight_Value(BigDecimal value);

}
