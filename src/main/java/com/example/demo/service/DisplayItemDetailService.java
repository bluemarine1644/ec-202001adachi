package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.Topping;
import com.example.demo.repository.ItemMapper;
import com.example.demo.repository.ToppingMapper;
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
    private ItemMapper itemMapper;
    @Autowired
    private ToppingMapper toppingMapper;

    public Item displayItem(Integer id) {
        return itemMapper.loadByItemId(id);
    }

    public List<Topping> displayItemDetail() {
        return toppingMapper.select();
    }
}
