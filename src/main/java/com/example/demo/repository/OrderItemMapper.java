package com.example.demo.repository;

import com.example.demo.domain.OrderItem;
import org.apache.ibatis.annotations.*;

/**
 * order_itemsテーブルを操作するマッパー.
 *
 * @author sota_adachi
 */
@Mapper
public interface OrderItemMapper {
    /**
     * 注文商品を登録します.
     *
     * @param orderItem 注文商品情報
     */
    @Insert("INSERT INTO order_items(item_id, order_id, quantity, size) VALUES(#{itemId}, #{orderId}, #{quantity}, #{size})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(OrderItem orderItem);

    @Delete("DELETE FROM order_items WHERE id = #{orderItemId}")
    void delete(Integer orderItemId);

    @Select("SELECT * FROM order_items WHERE id = #{orderItemId}")
    OrderItem select(Integer orderItemId);
}
