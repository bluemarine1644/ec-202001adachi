package com.example.demo.controller;

import com.example.demo.domain.LoginUser;
import com.example.demo.service.RemoveItemFromShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ショッピングカートから商品を削除するコントローラ.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class RemoveItemFromShoppingCartController {
    @Autowired
    private RemoveItemFromShoppingCartService removeItemFromShoppingCartService;

    @RequestMapping("/removeItemFromShoppingCart")
    public String removeItemFromShoppingCart(@AuthenticationPrincipal LoginUser loginUser, Integer orderItemId, Integer subTotal, Integer totalPrice) {
        removeItemFromShoppingCartService.removeItemFromShoppingCart(loginUser, orderItemId, subTotal, totalPrice);
        return "redirect:/displayTheItemOfShoppingCart";
    }
}
