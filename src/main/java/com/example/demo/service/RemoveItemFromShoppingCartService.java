package com.example.demo.service;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Order;
import com.example.demo.repository.OrderItemMapper;
import com.example.demo.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * ショッピングカートから商品を削除するサービス.
 * 
 * @author sota_adachi
 */
@Service
public class RemoveItemFromShoppingCartService {
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * ショッピングカートから商品を削除します.
     *
     * @param loginUser ログインユーザ情報
     * @param orderItemId 注文商品ID
     * @param subTotal 小計金額
     * @param totalPrice 合計金額
     */
    public void removeItemFromShoppingCart(@AuthenticationPrincipal LoginUser loginUser, Integer orderItemId, Integer subTotal, Integer totalPrice) {
        Integer userId;
        Integer status = 0;
        try {
            userId = loginUser.getUser().getId();
        } catch (NullPointerException exception) {
            userId = session.getId().hashCode();
        }
        orderItemMapper.delete(orderItemId);
        Order order = orderMapper.findByStatusAndUserId(status, userId);
        order.setTotalPrice(totalPrice - subTotal);
        orderMapper.updateTotalPriceByUserIdAndStatus(order);
    }
}
