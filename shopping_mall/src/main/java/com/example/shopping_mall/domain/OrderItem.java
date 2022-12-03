package com.example.shopping_mall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    public int getTotalPrice() {
        return this.orderPrice * this.count;
    }
}
