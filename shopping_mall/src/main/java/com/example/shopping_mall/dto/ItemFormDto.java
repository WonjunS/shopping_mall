package com.example.shopping_mall.dto;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    private String name;
    private String detail;

    private int price;
    private int quantity;

    private ItemSellStatus itemSellStatus;

}
