package com.example.shopping_mall.controller;

import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.repository.OrderRepository;
import com.example.shopping_mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

//    // 회원 정보 수정 페이지
//    @GetMapping("/update/{memberId}")
//    public String memberDetail(@PathVariable("memberId") Long memberId, Model model) {
//        try {
//            JoinFormDto joinFormDto = memberService.getMemberDetail(memberId);
//            model.addAttribute("joinFormDto", joinFormDto);
//        } catch(EntityNotFoundException e) {
//            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
//            model.addAttribute("joinFormDto", new JoinFormDto());
//        }
//
//        return "member/joinForm";
//    }
//
////    @PostMapping("/update")
////    public String memberUpdate(@Valid JoinFormDto joinFormDto, BindingResult bindingResult, Model model) {
////        if(bindingResult.hasErrors()) {
////            return "member/joinForm";
////        }
////
////        try {
////            memberService.updateMember(joinFormDto);
////        } catch (Exception e) {
////            model.addAttribute("errorMessage", "회원 정보 수정 중 에러가 발생했습니다.");
////            return "member/joinForm";
////        }
////        return "redirect:/";
////    }

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
