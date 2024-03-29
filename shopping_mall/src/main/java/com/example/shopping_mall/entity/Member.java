package com.example.shopping_mall.entity;

import com.example.shopping_mall.constant.Role;
import com.example.shopping_mall.dto.JoinFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTime {

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
    private Role role;

    public static Member createMember(JoinFormDto joinFormDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(joinFormDto.getName())
                .email(joinFormDto.getEmail())
                .password(passwordEncoder.encode(joinFormDto.getPassword()))
                .phone(joinFormDto.getPhone())
                .street(joinFormDto.getStreet())
                .city(joinFormDto.getCity())
                .zipcode(joinFormDto.getZipcode())
                .role(Role.USER)
                .build();
    }

    public void updateMember(JoinFormDto joinFormDto) {
        this.name = joinFormDto.getName();
        this.email = joinFormDto.getEmail();
        this.phone = joinFormDto.getPhone();
        this.street = joinFormDto.getStreet();
        this.city = joinFormDto.getCity();
        this.zipcode = joinFormDto.getZipcode();
    }

}
