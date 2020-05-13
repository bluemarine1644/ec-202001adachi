package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品一覧を表示するサービス.
 *
 * @author sota_adachi
 */
@Service
@Transactional
public class DisplayItemListService {
    @Autowired
    private ItemRepository itemRepository;

    /**
     * 商品一覧を表示します.
     *
     * @param name 商品名
     * @return 商品一覧情報
     */
    public List<Item> displayItemList(String name) {
        return itemRepository.selectItems(name);
    }
}
