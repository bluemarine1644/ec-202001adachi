package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ordersテーブルを操作するマッパー.
 *
 * @author sota_adachi
 */
@Mapper
public interface OrderMapper {
    /**
     * 注文情報を挿入します.
     *
     * @param order 注文情報
     */
    @Insert("INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method) VALUES(#{userId}, #{status}, #{totalPrice}, #{orderDate}, #{destinationName}, #{destinationEmail}, #{destinationZipcode}, #{destinationAddress}, #{destinationTel}, #{deliveryTime}, #{paymentMethod})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Order order);
    /**
     * 注文情報を検索します.
     *
     * @param status 注文状況
     * @param userId ユーザID
     * @return 注文情報
     */
    List<Order> findByStatusAndUserId(@Param("status") Integer status, @Param("userId") Integer userId);

    /**
     * 注文情報を更新します.
     *
     * @param order 注文情報
     */
    @Update("UPDATE orders SET total_price = #{totalPrice} WHERE user_id = #{userId} AND status = #{status};")
    void updateTotalPriceByUserIdAndStatus(Order order);
}