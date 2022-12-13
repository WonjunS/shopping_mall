package com.example.shopping_mall.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinFormDto {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipcode;

}
