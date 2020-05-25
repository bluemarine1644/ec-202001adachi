package com.example.demo.domain;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * 注文情報を表すドメイン.
 *
 * @author sota_adachi
 */
@Data
public class Order {
    /**
     * 注文ID
     */
    private Integer id;
    /**
     * ユーザID
     */
    private Integer userId;
    /**
     * 注文状況
     */
    private Integer status;
    /**
     * 注文合計金額
     */
    private Integer totalPrice;
    /**
     * 注文日時
     */
    private Date orderDate;
    /**
     * 注文者名
     */
    private String destinationName;
    /**
     * 注文者メールアドレス
     */
    private String destinationEmail;
    /**
     * 注文者郵便番号
     */
    private String destinationZipcode;
    /**
     * 注文者住所
     */
    private String destinationAddress;
    /**
     * 注文者電話番号
     */
    private String destinationTel;
    /**
     * 配達日時
     */
    private Timestamp deliveryTime;
    /**
     * 支払い方法
     */
    private Integer paymentMethod;
    /**
     * ユーザ情報
     */
    private User user;
    /**
     * 注文商品リスト
     */
    private List<OrderItem> orderItemList;
}
