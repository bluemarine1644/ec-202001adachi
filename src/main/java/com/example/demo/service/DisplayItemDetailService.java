package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Topping;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品詳細を表示するサービス.
 *
 * @author sota_adachi
 */
@Service
public class DisplayItemDetailService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ToppingRepository toppingRepository;

    public Item displayItem(Integer id) {
        return itemRepository.loadItem(id);
    }

    public List<Topping> displayItemDetail() {
        return toppingRepository.select();
    }
}
