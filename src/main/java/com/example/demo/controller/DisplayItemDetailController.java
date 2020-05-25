package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Topping;
import com.example.demo.service.DisplayItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品詳細を表示するコントローラ.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class DisplayItemDetailController {
    @Autowired
    private DisplayItemDetailService displayItemDetailService;

    @RequestMapping("/displayItemDetail")
    public String displayItemDetail(Integer itemId, Model model, @AuthenticationPrincipal LoginUser loginUser) {
        String userName;
        try {
            userName = loginUser.getUser().getName();
        } catch (NullPointerException exception) {
            userName = "ゲスト";
        }
        model.addAttribute("userName", userName);
        Item item = displayItemDetailService.displayItem(itemId);
        model.addAttribute("item", item);
        List<Topping> toppingList = displayItemDetailService.displayItemDetail();
        model.addAttribute("toppingList", toppingList);
        return "/item_detail";
    }
}
