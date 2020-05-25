package com.example.demo.service;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.repository.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ショッピングカートの中身を表示するサービス.
 *
 * @author sota_adachi
 */
@Service
public class displayTheItemOfShoppingCartService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private HttpSession session;

    /**
     * ショッピングカートの中身を表示します.
     *
     * @param status 注文状況（注文前）
     * @param userId ユーザID
     * @return 注文前の注文情報
     */
    public Order displayTheItemOfShoppingCart(Integer status, Integer userId) {
        // 小計を計算してリストに格納する
        Order order = orderMapper.findByStatusAndUserId(status, userId);
        List<OrderItem> orderItemList;
        try {
            orderItemList = order.getOrderItemList();
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
        for (OrderItem orderItem : orderItemList) {
            Integer itemPrice;
            Integer toppingPrice;
            if (orderItem.getSize() == 'M') {
                itemPrice = orderItem.getItem().getPriceM();
                toppingPrice = orderItem.getOrderToppingList().size() * 200;
            } else {
                itemPrice = orderItem.getItem().getPriceL();
                toppingPrice = orderItem.getOrderToppingList().size() * 300;
            }
            orderItem.setSubTotal((itemPrice + toppingPrice) * orderItem.getQuantity());
        }
        return order;
    }
}
