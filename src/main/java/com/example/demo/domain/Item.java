package com.example.demo.domain;

import lombok.Data;

/**
 * 商品情報を表すドメイン.
 *
 * @author sota_adachi
 */
@Data
public class Item {
    /**
     * 商品ID
     */
    private Integer id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品説明
     */
    private String description;
    /**
     * 商品価格（Mサイズ）
     */
    private Integer priceM;
    /**
     * 商品価格（Lサイズ）
     */
    private Integer priceL;
    /**
     * 商品画像パス
     */
    private String imagePath;
    /**
     * 削除コード
     */
    private boolean deleted;
}
