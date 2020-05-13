package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.domain.LoginUser;
import com.example.demo.form.DisplayItemListForm;
import com.example.demo.service.DisplayItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 商品一覧情報を表示するコントローラ.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class DisplayItemListController {
    @Autowired
    private DisplayItemListService displayItemListService;

    @ModelAttribute
    public DisplayItemListForm setUpDisplayItemListForm() {
        return new DisplayItemListForm();
    }

    @RequestMapping("/")
    public String displayItemList(Model model, @AuthenticationPrincipal LoginUser loginUser, DisplayItemListForm form) {
        System.out.println(loginUser.getUser().getName() + "さんがログイン中");
        List<Item> itemList = displayItemListService.displayItemList(form.getName());
        model.addAttribute("itemList", itemList);
        return "/item_list_pizza";
    }
}
