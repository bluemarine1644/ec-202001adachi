package com.example.demo.domain;

import lombok.Data;

/**
 * ユーザ情報を表すドメイン.
 *
 * @author sota_adachi
 */
@Data
public class User {
    /**
     * id
     */
    private Integer id;
    /**
     * 登録ユーザ名
     */
    private String name;
    /**
     * メールアドレス
     */
    private String email;
    /**
     * パスワード
     */
    private String password;
    /**
     * 郵便番号
     */
    private String zipcode;
    /**
     * 住所
     */
    private String address;
    /**
     * 電話番号
     */
    private String telephone;
}
