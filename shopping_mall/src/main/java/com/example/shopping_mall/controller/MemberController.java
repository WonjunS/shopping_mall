package com.example.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {

    public String signup() {
        return "signup";
    }

    public String create() {
        return "login";
    }

    public String login() {
        return "members/loginForm";
    }
}
