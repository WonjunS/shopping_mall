package com.example.shopping_mall.repository;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void save() {
        // given
        String name = "Name 1";
        Item item = Item.builder()
                .itemName(name)
                .itemDetail("테스트 상품 상세 설명")
                .price(10000)
                .quantity(10)
                .itemSellStatus(ItemSellStatus.SELL)
                .build();

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertEquals(name, savedItem.getItemName());
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNameTest(){
        this.save();
        List<Item> itemList = itemRepository.findByItemName("Name 1");
        for(Item item : itemList) {
            System.out.println("Item: " + item.getItemName());
        }
    }

    @Test
    @DisplayName("상품 가격 조회 테스트")
    public void findByItemPriceTest(){
        this.save();
        List<Item> itemList = itemRepository.findByPriceLessThan(20000);
        for(Item item : itemList) {
            System.out.println("Item: " + item.getPrice());
        }
    }

}