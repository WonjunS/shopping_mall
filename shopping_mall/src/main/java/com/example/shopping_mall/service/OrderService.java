package com.example.shopping_mall.service;

import com.example.shopping_mall.domain.Item;
import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.domain.Order;
import com.example.shopping_mall.domain.OrderItem;
import com.example.shopping_mall.dto.OrderDto;
import com.example.shopping_mall.repository.ItemRepository;
import com.example.shopping_mall.repository.MemberRepository;
import com.example.shopping_mall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 주문 하기
    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getId())
                .orElseThrow(EntityNotFoundException::new);

        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}
