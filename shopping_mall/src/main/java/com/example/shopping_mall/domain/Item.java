package com.example.shopping_mall.domain;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.dto.ItemFormDto;
import com.example.shopping_mall.exception.OutOfStackException;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;
    private String itemDetail;

    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    public void updateItem(ItemFormDto itemFormDto) {
        this.itemName = itemFormDto.getItemName();
        this.itemDetail = itemFormDto.getItemDetail();
        this.price = itemFormDto.getPrice();
        this.quantity = itemFormDto.getQuantity();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void addStock(int quantity) {
        this.quantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if(restStock < 0) {
            throw new OutOfStackException("상품의 재고가 부족합니다.");
        }
        this.quantity = restStock;
    }
}
