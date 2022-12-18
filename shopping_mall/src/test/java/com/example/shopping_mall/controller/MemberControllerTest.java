package com.example.shopping_mall.controller;

import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password) {
        JoinFormDto joinFormDto = new JoinFormDto();
        joinFormDto.setName("홍길동1");
        joinFormDto.setEmail(email);
        joinFormDto.setPassword(password);
        joinFormDto.setPhone("123-4567");
        joinFormDto.setStreet("1 Street");
        joinFormDto.setCity("Seoul");
        joinFormDto.setZipcode("1234");

        Member member = Member.createMember(joinFormDto, passwordEncoder);

        return memberService.save(member);
    }

    @Test
    @DisplayName("로그인 테스트")
    public void login() throws Exception {
        String email = "abcdef@gmail.com";
        String password = "123456";
        this.createMember(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/member/login")
                .user(email).password(password))
            .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception {

        String email = "abcdef@gmail.com";
        String password = "123456";
        this.createMember(email, password);

        mockMvc.perform(formLogin().loginProcessingUrl("/member/login")
                        .userParameter("email").user(email).password("12345"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

}