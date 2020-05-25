package com.example.demo.form;

import lombok.Data;

/**
 * 商品を検索する際に使用するフォーム.
 *
 * @author sota_adachi
 */
@Data
public class DisplayItemListForm {
    /**
     * 検索商品名
     */
    private String name;
    /**
     * 並び替え
     */
    private Integer sort;
}
