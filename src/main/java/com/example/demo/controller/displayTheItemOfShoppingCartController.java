package com.example.demo.controller;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Order;
import com.example.demo.service.displayTheItemOfShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * ショッピングカートの中身を表示するコントローラ.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class displayTheItemOfShoppingCartController {
    @Autowired
    private displayTheItemOfShoppingCartService displayTheItemOfShoppingCartService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/displayTheItemOfShoppingCart")
    public String displayTheItemOfShoppingCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {
        String userName;
        try {
            userName = loginUser.getUser().getName();
        } catch (NullPointerException exception) {
            userName = "ゲスト";
        }
        model.addAttribute("userName", userName);
        Integer status = 0;
        Integer userId;
        try {
            userId = loginUser.getUser().getId();
        } catch (NullPointerException exception) {
            userId = session.getId().hashCode();
        }
        try {
            Order order = displayTheItemOfShoppingCartService.displayTheItemOfShoppingCart(status, userId).get(0);
            Integer orderItemSize = order.getOrderItemList().size();
            model.addAttribute("order", order);
            model.addAttribute("orderItemSize", orderItemSize);
        } catch (NullPointerException exception) {
            model.addAttribute("orderItemSize", 0);
        }
        System.out.println(model);
        return "/cart_list";
    }
}
