package com.example.shopping_mall.repository;

import com.example.shopping_mall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

    List<Item> findByItemName(String name);

    List<Item> findByPriceLessThan(int price);

}
