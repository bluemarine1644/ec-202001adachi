package com.example.demo.domain;

import lombok.Data;

/**
 * トッピング情報を表すドメイン.
 *
 * @author sota_adachi
 */
@Data
public class Topping {
    /**
     * トッピングID
     */
    private Integer id;
    /**
     * トッピング名
     */
    private String name;
    /**
     * Mサイズの価格
     */
    private Integer priceM;
    /**
     * Lサイズの価格
     */
    private Integer priceL;
}
