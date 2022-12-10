package com.example.shopping_mall.domain;

import com.example.shopping_mall.constant.MemberType;
import com.example.shopping_mall.dto.JoinFormDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private String street;

    private String city;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateDetails(JoinFormDto joinFormDTO) {
        this.name = joinFormDTO.getName();
        this.email = joinFormDTO.getEmail();
        this.phone = joinFormDTO.getPassword();
        this.street = joinFormDTO.getStreet();
        this.city = joinFormDTO.getCity();
        this.zipcode = joinFormDTO.getZipcode();
    }
}
