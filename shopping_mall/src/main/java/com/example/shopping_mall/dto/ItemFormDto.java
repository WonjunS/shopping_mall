package com.example.shopping_mall.dto;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.domain.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotNull
    private String name;

    @NotBlank
    private String detail;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    private ItemSellStatus itemSellStatus;

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    private ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }

}
