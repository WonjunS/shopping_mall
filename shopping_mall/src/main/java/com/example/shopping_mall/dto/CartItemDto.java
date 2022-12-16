package com.example.shopping_mall.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CartItemDto {

    @NotNull
    private Long id;

    @Min(value = 1)
    private int count;

}
