package com.example.shopping_mall.repository;

import com.example.shopping_mall.constant.ItemSellStatus;
import com.example.shopping_mall.entity.Item;
import com.example.shopping_mall.entity.QItem;
import com.example.shopping_mall.entity.QItemImg;
import com.example.shopping_mall.dto.ItemSearchDto;
import com.example.shopping_mall.dto.MainItemDto;
import com.example.shopping_mall.dto.QMainItemDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    // 생성자 DI를 통해서 JPAQueryFactory(EntityManager) 주입
    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 상품 등록일에 대한 조회 조건
    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d",searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w",searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m",searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m",searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QItem.item.createdDate.after(dateTime);
    }

    // 상품 상태에 대한 조회 조건
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    // 상품명 또는 등록자 아이디에 대한 조회 조건
    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("name", searchBy)) {
            return QItem.item.itemName.like("%" + searchQuery + "%");
        }
//        else if (StringUtils.equals("createdBy", searchBy)) {
//            return QItem.item.createdDate.like("%" + searchQuery + "%");
//        }
        return null;
    }

    private BooleanExpression itemNameLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemName.like("%" + searchQuery + "%");
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        // queryFactory 를 이용하여 쿼리문 생성
        QueryResults<Item> results = queryFactory
                .selectFrom(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        // 조회 대상 리스트 결과
        List<Item> content = results.getResults();

        // 조회 대상 리스트의 개수(count)
        long total = results.getTotal();

        // Page 인터페이스를 구현한 PageImpl 객체 반환
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDto> result = queryFactory
                .select(
                        new QMainItemDto(
                                item.id,
                                item.itemName,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price)
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repImgYn.eq("Y"))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto> content = result.getResults();
        long total = result.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
