package com.example.shopping_mall.service;

import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입")
    public void createMember() {
        // given
        JoinFormDto joinFormDto = new JoinFormDto();
        joinFormDto.setName("홍길동");
        joinFormDto.setEmail("abcdef@gmail.com");
        joinFormDto.setPassword("123456");
        joinFormDto.setPhone("123-4567");
        joinFormDto.setStreet("1 Street");
        joinFormDto.setCity("Seoul");
        joinFormDto.setZipcode("1234");

        // when
        Member member = Member.createMember(joinFormDto, passwordEncoder);
        Member savedMember = memberService.save(member);

        // then
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getPhone(), savedMember.getPhone());
        assertEquals(member.getStreet(), savedMember.getStreet());
        assertEquals(member.getCity(), savedMember.getCity());
        assertEquals(member.getZipcode(), savedMember.getZipcode());
    }

    @Test
    @DisplayName("회원가입 중복 테스트")
    public void createMemberDuplication() throws Exception {
        // given
        JoinFormDto joinFormDto = new JoinFormDto();
        joinFormDto.setName("홍길동");
        joinFormDto.setEmail("abcd@gmail.com");
        joinFormDto.setPassword("123456");
        joinFormDto.setPhone("123-4567");
        joinFormDto.setStreet("1 Street");
        joinFormDto.setCity("Seoul");
        joinFormDto.setZipcode("1234");

        // when
        Member member = Member.createMember(joinFormDto, passwordEncoder);
        Exception exception = assertThrows(IllegalStateException.class, () -> memberService.save(member));

        // then
        assertEquals("이미 존재하는 회원입니다.", exception.getMessage());
    }
}