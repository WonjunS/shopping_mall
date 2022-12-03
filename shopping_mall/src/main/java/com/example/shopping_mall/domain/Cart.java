package com.example.shopping_mall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
public class Cart {

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;
}
