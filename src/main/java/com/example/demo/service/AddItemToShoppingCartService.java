package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
import com.example.demo.form.AddItemToShoppingCartForm;
import com.example.demo.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * ショッピングカートに商品を追加するサービス.
 *
 * @author sota_adachi
 */
@Service
public class AddItemToShoppingCartService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderToppingMapper orderToppingMapper;

    @Autowired
    private HttpSession session;

    /**
     * ショッピングカートに商品を追加します.
     *
     * @param form フォーム
     */
    public void addItemToShoppingCart(AddItemToShoppingCartForm form, Integer userId) {
        // ordersテーブルからログイン中のユーザID(ゲストの場合はセッションIDをハッシュ化)を元に注文前(status = 0)のデータを検索する
        Integer status = 0;
        Integer totalPrice = 0;
        Order order = orderMapper.findByStatusAndUserId(status, userId);
        if (Objects.isNull(order)) {
            // 他に注文前の商品がない場合はユーザID、注文状況（注文前）、合計金額（NotNull制約があるため）をセットする
            order = new Order();
            order.setUserId(userId);
            order.setStatus(status);
            order.setTotalPrice(totalPrice);
            // ordersに登録すると同時に自動生成された注文IDをセットする
            orderMapper.insert(order);
        } else {
            // 他に注文前の商品がある場合は合計金額を取得する
            totalPrice = order.getTotalPrice();
        }

        // orderItemに情報を入れる
        OrderItem orderItem = new OrderItem();
        // 注文IDをセットする
        orderItem.setOrderId(order.getId());
        // form内の情報をセットする
        BeanUtils.copyProperties(form, orderItem);
        // orderItemの情報をorder_itemsに登録する
        orderItemMapper.insert(orderItem);

        // orderToppingをセットしorder_toppingsに登録していく
        for (int i = 0; i < form.getOrderToppingIdList().size(); i++) {
            OrderTopping orderTopping = new OrderTopping();
            orderTopping.setToppingId(form.getOrderToppingIdList().get(i));
            orderTopping.setOrderItemId(orderItem.getId());
            orderToppingMapper.insert(orderTopping);
        }

        // 合計金額を更新してセットし更新する
        totalPrice += form.getSubTotal();
        order.setTotalPrice(totalPrice);
        orderMapper.updateTotalPriceByUserIdAndStatus(order);
    }
}
