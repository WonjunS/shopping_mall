package com.example.shopping_mall.repository;

import com.example.shopping_mall.domain.Item;
import com.example.shopping_mall.dto.ItemSearchDto;
import com.example.shopping_mall.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
