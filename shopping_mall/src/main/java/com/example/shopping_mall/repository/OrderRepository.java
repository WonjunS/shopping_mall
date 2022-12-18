package com.example.shopping_mall.repository;

import com.example.shopping_mall.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o " +
           "where o.member.email = :email "+
           "order by o.orderDate desc")
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Order o " +
           "where o.member.email = :email")
    Long countOrders(String email);
}
