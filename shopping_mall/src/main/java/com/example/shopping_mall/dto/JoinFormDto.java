package com.example.shopping_mall.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String phone;

    @NotBlank(message = "상세 주소는 필수 입력 값입니다.")
    private String street;

    @NotBlank(message = "도로명 주소는 필수 입력 값입니다.")
    private String city;

    @NotBlank(message = "우편번호는 필수 입력 값입니다.")
    private String zipcode;

}
