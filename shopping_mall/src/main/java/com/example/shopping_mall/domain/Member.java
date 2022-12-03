package com.example.shopping_mall.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;

    @Embedded
    private Address address;
}
