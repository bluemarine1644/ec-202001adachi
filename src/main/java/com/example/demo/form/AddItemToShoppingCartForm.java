package com.example.demo.form;

import lombok.Data;

import java.util.List;

/**
 * ショッピングカートに商品を追加する際に使用するフォーム.
 *
 * @author sota_adachi
 */
@Data
public class AddItemToShoppingCartForm {
    /**
     * 商品ID
     */
    private Integer itemId;
    /**
     * サイズ
     */
    private Character size;
    /**
     * 注文トッピングIDリスト
     */
    private List<Integer> orderToppingIdList;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 小計金額
     */
    private Integer subTotal;
}
