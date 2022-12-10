package com.example.shopping_mall.repository;

import com.example.shopping_mall.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);

    List<Item> findByPriceLessThan(int price);

}
