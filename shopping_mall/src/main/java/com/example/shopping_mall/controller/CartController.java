package com.example.shopping_mall.controller;

import com.example.shopping_mall.dto.CartItemDto;
import com.example.shopping_mall.dto.CartListDto;
import com.example.shopping_mall.dto.CartOrderDto;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.entity.Member;
import com.example.shopping_mall.repository.MemberRepository;
import com.example.shopping_mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final MemberRepository memberRepository;
    private final CartService cartService;

    // 장바구니 담기
    @PostMapping(value = "/checkout")
    @ResponseBody
    public ResponseEntity cart(@RequestBody @Valid CartItemDto cartItemDto,
                               BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long cartItemId;

        try {
            cartItemId = cartService.addToCart(cartItemDto, principal.getName());
        } catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    // 체크아웃 페이지
    @GetMapping(value = "/checkout")
    public String cartList(Principal principal, Model model) {
        List<CartListDto> cartListDtos = cartService.getCartList(principal.getName());
        Member member = memberRepository.findByEmail(principal.getName());
        JoinFormDto joinFormDto = JoinFormDto.of(member);

        model.addAttribute("cartItems", cartListDtos);
        model.addAttribute("joinFormDto", joinFormDto);

        return "order/orderCheckout";
    }

    // 장바구니 수량 변경
    @PatchMapping(value = "/cartItem/{cartItemId}")
    @ResponseBody
    public ResponseEntity updateCartItem(@PathVariable(name = "cartItemId") Long cartItemId,
                                         int count, Principal principal) {
        if(!cartService.validateCartItem(cartItemId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.updateCartItemCount(cartItemId, count);
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    // 장바구니 삭제
    @DeleteMapping(value = "/cartItem/{cartItemId}")
    @ResponseBody
    public ResponseEntity deleteCartItem(@PathVariable(name = "cartItemId") Long cartItemId, Principal principal) {
        if(!cartService.validateCartItem(cartItemId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartItem(cartItemId);
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    // 장바구니 상품 주문
    @PostMapping(value = "/cart/orders")
    @ResponseBody
    public ResponseEntity orders(@RequestBody CartOrderDto cartOrderDto, Principal principal) throws Exception {
        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();

        if(cartOrderDtoList == null || cartOrderDtoList.size() == 0) {
            return new ResponseEntity<String>("주문할 상품을 선택해주세요.", HttpStatus.BAD_REQUEST);
        }

        for(CartOrderDto cartOrderDto1 : cartOrderDtoList) {
            if(!cartService.validateCartItem(cartOrderDto1.getCartItemId(), principal.getName())) {
                return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN);
            }
        }

        Long orderId = cartService.orderCartItem(cartOrderDtoList, principal.getName());

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }
}
