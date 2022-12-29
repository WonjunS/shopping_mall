package com.example.shopping_mall.controller;

import com.example.shopping_mall.entity.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.repository.OrderRepository;
import com.example.shopping_mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    // 회원가입 페이지
    @GetMapping("/new")
    public String memberForm(JoinFormDto joinFormDto, Model model) {
        model.addAttribute("joinFormDto", joinFormDto);
        return "member/joinForm";
    }

    @PostMapping("/new")
    public String createMember(@Valid JoinFormDto joinFormDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "member/joinForm";
        }

        try {
            Member member = Member.createMember(joinFormDto, passwordEncoder);
            memberService.save(member);
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/joinForm";
        }
        return "redirect:/";
    }

    @GetMapping("/list")
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        Map<Long, Long> orderCounts = new HashMap<>();
        for(Member m : members) {
            Long count = orderRepository.countOrders(m.getEmail());
            orderCounts.put(m.getId(), count);
        }
        model.addAttribute("members", members);
        model.addAttribute("orderCounts", orderCounts);

        return "/member/memberList";
    }

    // TODO: 회원정보 수정 구현
    // 참고 링크: https://mycodearchive.tistory.com/213
    // https://parkground.tistory.com/722

    // 회원 정보 불러오기
    @GetMapping("/info")
    public String memberInfo(Model model, Principal principal) {
        String email = principal.getName();
        Member details = memberService.findMember(email);

        model.addAttribute("details", details);

        return "/member/memberInfo";
    }

    // 회원 정보 업데이트
    @PostMapping("/info")
    public String memberInfoUpdate(@Valid JoinFormDto joinFormDto, Model model, Principal principal) {
        String email = principal.getName();


        return "redirect:/";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginMember() {
        return "member/loginForm";
    }

    // 로그인 중 오류가 발생했을 때
    @GetMapping("/login/fail")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/loginForm";
    }

}
