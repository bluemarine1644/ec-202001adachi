package com.example.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * 注文商品情報を表すドメイン.
 *
 * @author sota_adachi
 */
@Data
public class OrderItem {
    /**
     * 注文商品ID
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer itemId;
    /**
     * 注文ID
     */
    private Integer orderId;
    /**
     * 注文商品量
     */
    private Integer quantity;
    /**
     * 注文商品サイズ
     */
    private Character size;
    /**
     * 商品情報
     */
    private Item item;
    /**
     * 注文トッピングリスト
     */
    private List<OrderTopping> orderToppingList;
    /**
     * 小計金額
     */
    private Integer subTotal;
}
