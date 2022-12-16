package com.example.shopping_mall.dto;

import com.example.shopping_mall.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private String itemName;

    private int count;
    private int orderPrice;

    private String imgUrl;

    public OrderItemDto(OrderItem orderItem, String imgUrl) {
        this.itemName = orderItem.getItem().getName();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getPrice();
        this.imgUrl = imgUrl;
    }

}