package com.example.shopping_mall.dto;

import com.example.shopping_mall.constant.ItemSellStatus;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {

    private Long id;

    private String name;

    private String detail;

    private int price;

    private ItemSellStatus itemSellStatus;

}
