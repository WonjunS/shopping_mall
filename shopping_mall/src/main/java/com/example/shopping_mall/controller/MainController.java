package com.example.shopping_mall.controller;

import com.example.shopping_mall.domain.Item;
import com.example.shopping_mall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping(value = "/")
    public String main(Model model) {
        List<Item> list = itemService.findItems();
        model.addAttribute("list", list);
        return "main";
    }
}
