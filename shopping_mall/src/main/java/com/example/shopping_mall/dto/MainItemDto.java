package com.example.shopping_mall.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;
    private String itemName;
    private String itemDetail;
    private String itemUrl;
    private int price;

    @QueryProjection
    public MainItemDto(Long id, String itemName, String itemDetail, String itemUrl, int price) {
        this.id = id;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.itemUrl = itemUrl;
        this.price = price;
    }
}
