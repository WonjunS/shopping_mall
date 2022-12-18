package com.example.shopping_mall.service;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.domain.CartItem;
import com.example.shopping_mall.domain.Item;
import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.CartItemDto;
import com.example.shopping_mall.repository.CartItemRepository;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem() {
        Item item = new Item();
        item.setName("테스트 상품");
        item.setPrice(20000);
        item.setDetail("테스트 상품 상세 설명");
        item.setQuantity(40);
        item.setItemSellStatus(ItemSellStatus.SELL);

        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    public Long addCart() {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(item.getId());
        cartItemDto.setCount(10);

        return cartService.addToCart(cartItemDto, member.getEmail());
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    void addToCart() {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(item.getId());
        cartItemDto.setCount(10);

        Long cartItemId = cartService.addToCart(cartItemDto, member.getEmail());
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getCount(), cartItem.getCount());
    }

    @Test
    @DisplayName("장바구니 수량 변경 테스트")
    void updateCartItemCount() {
        Long cartItemId = addCart();
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(50);
        int count = cartItem.getCount();
        assertEquals(count, 50);
    }
}