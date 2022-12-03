package com.example.shopping_mall.domain;

import com.example.shopping_mall.exception.OutOfStackException;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private String detail;

    private int price;
    private int stockQuantity;

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new OutOfStackException("상품의 재고가 부족합니다.");
        }
        this.stockQuantity = restStock;
    }
}
