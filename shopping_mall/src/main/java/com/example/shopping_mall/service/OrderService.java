package com.example.shopping_mall.service;

import com.example.shopping_mall.entity.*;
import com.example.shopping_mall.dto.MailDto;
import com.example.shopping_mall.dto.OrderDto;
import com.example.shopping_mall.dto.OrderHistDto;
import com.example.shopping_mall.dto.OrderItemDto;
import com.example.shopping_mall.repository.ItemImgRepository;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.repository.MemberRepository;
import com.example.shopping_mall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;
    private final MailService mailService;

    // 주문 하기
    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        MailDto mailDto = new MailDto();
        mailDto.setAddress(member.getEmail());
        mailDto.setTitle("Your order");
        mailDto.setMessage(Integer.toString(order.getTotalPrice()));
        mailService.mailSend(mailDto);

        return order.getId();
    }

    // 주문 내역 조회
    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrders(email);

        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        for(Order order : orders) {
            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }
            orderHistDtos.add(orderHistDto);
        }
        return new PageImpl<>(orderHistDtos, pageable, totalCount);
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        if(StringUtils.equals(order.getMember().getEmail(), email)) {
            return true;
        }
        return false;
    }

    // 주문 취소
    public void orderCancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.orderCancel();
    }

    // 장바구니 상품들 주문
    public Long orders(List<OrderDto> orderDtoList, String email) {
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        for(OrderDto orderDto : orderDtoList) {
            Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        MailDto mailDto = new MailDto();
        mailDto.setAddress(member.getEmail());
        mailDto.setTitle("Your order");
        mailDto.setMessage(Integer.toString(order.getTotalPrice()));
        mailService.mailSend(mailDto);

        return order.getId();
    }
}
