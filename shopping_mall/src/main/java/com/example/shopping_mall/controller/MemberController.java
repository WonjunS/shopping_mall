package com.example.shopping_mall.controller;

import com.example.shopping_mall.entity.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.repository.OrderRepository;
import com.example.shopping_mall.service.MailService;
import com.example.shopping_mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    private final MailService mailService;

    // 회원가입 페이지
    @GetMapping("/new")
    public String memberForm(JoinFormDto joinFormDto, Model model) {
        model.addAttribute("joinFormDto", joinFormDto);
        return "member/joinForm";
    }

    // 생성된 이메일 인증 코드를 리턴
    @RequestMapping(value = "/emailAuth", method = RequestMethod.GET)
    @ResponseBody
    public String emailAuth(@RequestParam("email") String email) throws Exception {
        String code = mailService.sendVerificationCode(email);
        System.out.println(code);
        return code;
    }

    // 회원가입 폼에 있는 정보를 MySQL db에 저장
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

    // 회원 목록 불러오기 (Admin 계정에서만 확인 가능)
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

        return "member/memberList";
    }

    // 회원 정보 불러오기
    @GetMapping("/info")
    public String memberInfo(Model model, Principal principal) {
        String email = principal.getName();
        Member details = memberService.findMember(email);

        model.addAttribute("details", details);
        model.addAttribute("name", details.getName());
        model.addAttribute("id", details.getId());

        return "member/memberInfo";
    }

    // 회원 정보 수정 페이지
    @GetMapping("/update/{memberId}")
    public String update(@PathVariable(name = "memberId") Long memberId, Model model) {
        try {
            JoinFormDto joinFormDto = memberService.getMemberDetail(memberId);
            model.addAttribute("joinFormDto", joinFormDto);
        } catch(EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
            model.addAttribute("joinFormDto", new JoinFormDto());
        }
        return "member/joinForm";
    }

    // 회원 정보 업데이트
    @PostMapping("/update/{memberId}")
    public String memberInfoUpdate(@Valid JoinFormDto joinFormDto, BindingResult bindingResult, Model model) {
        Member member = memberService.findMember(joinFormDto.getEmail());
        joinFormDto.setId(member.getId());

        if(bindingResult.hasErrors()) {
            return "member/joinForm";
        }
        try {
            memberService.updateMember(joinFormDto);
        } catch(Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생했습니다.");
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
    @GetMapping("/login/fail")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/loginForm";
    }

}
