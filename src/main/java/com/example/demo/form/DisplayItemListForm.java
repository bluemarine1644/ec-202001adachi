package com.example.demo.form;

import lombok.Data;

/**
 * 商品一覧を表示するフォーム.
 *
 * @author sota_adachi
 */
@Data
public class DisplayItemListForm {
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
