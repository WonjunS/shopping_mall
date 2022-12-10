//package com.example.shopping_mall.controller;
//
//import com.example.shopping_mall.domain.Item;
//import com.example.shopping_mall.service.ItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class ItemController {
//
//    private final ItemService itemService;
//
////    @GetMapping
////    public ResponseEntity<ItemResponseDTO> getItem(Long id) {
////        ItemResponseDTO itemResponseDTO = itemService.getItem(id);
////
////        return ResponseEntity.status(HttpStatus.OK).body(itemResponseDTO);
////    }
////
////    @GetMapping
////    public Iterable<Item> list() {
////        return itemRepository.findAll();
////    }
//
//    @GetMapping("/item")
//    public String List(Model model) {
//        List<Item> list = itemService.findItems();
//        model.addAttribute("list", list);
//        return "main";
//    }
//}
