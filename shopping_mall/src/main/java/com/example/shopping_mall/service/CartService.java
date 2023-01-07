package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.Cart;
import com.example.shopping_mall.entity.CartItem;
import com.example.shopping_mall.entity.Item;
import com.example.shopping_mall.entity.Member;
import com.example.shopping_mall.dto.*;
import com.example.shopping_mall.repository.CartItemRepository;
import com.example.shopping_mall.repository.CartRepository;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    // 장바구니 담기
    public Long addToCart(CartItemDto cartItemDto, String email) {
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        if(cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(cartItem == null) {
            cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
        } else {
            cartItem.addCount(cartItemDto.getCount());
        }

        return cartItem.getId();
    }

    // 장바구니 조회
    @Transactional(readOnly = true)
    public List<CartListDto> getCartList(String email) {
        List<CartListDto> cartListDtos = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        if(cart == null) {
            return cartListDtos;
        }

        cartListDtos = cartItemRepository.findCartListDto(cart.getId());

        return cartListDtos;
    }

    // 장바구니 상품 수량 변경
    public void updateCartItemCount(Long cartItemId, int count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(count);
    }

    // 장바구니 삭제
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    // 장바구니에 있는 상품들 주문
    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email) throws Exception {

        List<OrderDto> orderDtoList = new ArrayList<>();

        for(CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderDto orderDto = new OrderDto();
            orderDto.setItemId(cartItem.getItem().getId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
        }

        Long orderId = orderService.orders(orderDtoList, email);

        for(CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }

        return orderId;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email) {
        Member currMember = memberRepository.findByEmail(email);

        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();

        if(StringUtils.equals(currMember.getEmail(), savedMember.getEmail())) {
            return true;
        }
        return false;
    }
}
