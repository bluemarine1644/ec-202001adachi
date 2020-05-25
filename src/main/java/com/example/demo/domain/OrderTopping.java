package com.example.demo.domain;

import lombok.Data;

/**
 * 注文トッピング情報を表すドメイン.
 *
 * @author sota_adachi
 */
@Data
public class OrderTopping {
    /**
     * 注文トッピングID
     */
    private Integer id;
    /**
     * トッピングID
     */
    private Integer toppingId;
    /**
     * 注文商品ID
     */
    private Integer orderItemId;
    /**
     * 注文トッピング情報
     */
    private Topping topping;
}
