package com.example.demo.repository;

import com.example.demo.domain.OrderTopping;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * order_toppingsテーブルを操作するマッパー.
 *
 * @author sota_adachi
 */
@Mapper
public interface OrderToppingMapper {
    /**
     * 注文トッピングを登録します.
     *
     * @param orderTopping 注文トッピング
     */
    @Insert("INSERT INTO order_toppings(topping_id, order_item_id) VALUES(#{toppingId}, #{orderItemId})")
    void insert(OrderTopping orderTopping);
}
