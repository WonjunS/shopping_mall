package com.example.shopping_mall.service;

import com.example.shopping_mall.domain.Item;
import com.example.shopping_mall.dto.ItemFormDto;
import com.example.shopping_mall.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Long saveItem(ItemFormDto itemFormDto) throws Exception{

        // 상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        return item.getId();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Long updateItem(ItemFormDto itemFormDto) {
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);

        item.updateItem(itemFormDto);

        return item.getId();
    }

}
