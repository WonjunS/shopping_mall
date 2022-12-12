package com.example.shopping_mall.service;

import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입")
    public void createMember() {
        JoinFormDto joinFormDto = new JoinFormDto();
        joinFormDto.setName("홍길동");
        joinFormDto.setEmail("abc@gmail.com");
        joinFormDto.setPassword("123456");
        joinFormDto.setPhone("123-4567");
        joinFormDto.setStreet("1 Street");
        joinFormDto.setCity("Seoul");
        joinFormDto.setZipcode("1234");

        Member member = Member.createMember(joinFormDto);
        Member savedMember = memberService.save(member);

        assertEquals(member.getName(), savedMember.getName());
    }
}