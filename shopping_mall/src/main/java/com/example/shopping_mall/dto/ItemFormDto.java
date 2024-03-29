package com.example.shopping_mall.dto;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotNull(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotBlank(message = "상품 상세는 필수 입력 사항입니다.")
    private String itemDetail;

    @NotNull(message = "상품 가격은 필수 입력 값입니다.")
    private int price;

    @NotNull(message = "상품 수량은 필수 입력 값입니다.")
    private int quantity;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }

}
