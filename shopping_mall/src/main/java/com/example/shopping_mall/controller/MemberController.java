package com.example.shopping_mall.controller;

import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 페이지
    @GetMapping("/new")
    public String memberForm(Model model) {
        return "member/joinForm";
    }

    @PostMapping("/new")
    public String createMember(@Valid JoinFormDto joinFormDto, BindingResult result) {
        if(result.hasErrors()) {
            return "member/joinForm";
        }

        try {
            Member member = Member.createMember(joinFormDto, passwordEncoder);
            memberService.save(member);
        } catch(IllegalStateException e) {
            return "member/joinForm";
        }
        return "redirect:/";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginMember() {
        return "member/loginForm";
    }

    // 로그인 중 오류가 발생했을 때
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/loginForm";
    }

}
