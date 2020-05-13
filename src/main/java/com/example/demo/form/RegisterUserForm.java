package com.example.demo.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * ユーザを登録するフォーム.
 *
 * @author sota_adachi
 */
@Data
public class RegisterUserForm {
    /**
     * id
     */
    private Integer id;
    /**
     * 登録ユーザ名
     */
    @NotBlank(message = "！名前が入力されていません！")
    private String name;
    /**
     * メールアドレス
     */
    @Email(message = "！形式が不正です！")
    @NotBlank(message = "！メールアドレスが入力されていません！")
    private String email;
    /**
     * パスワード
     */
    @NotBlank(message = "！パスワードが入力されていません！")
    private String password;
    /**
     * 確認用パスワード
     */
    @NotBlank(message = "！確認用パスワードが入力されていません！")
    private String confirmationPassword;
    /**
     * 郵便番号
     */
    @NotBlank(message = "！郵便番号が入力されていません！")
    private String zipcode;
    /**
     * 住所（都道府県以降）
     */
    @NotBlank(message = "！住所が入力されていません！")
    private String address;
    /**
     * 電話番号
     */
    @NotBlank(message = "！電話番号が入力されていません！")
    private String telephone;
}
