package com.example.demo.controller;

import com.example.demo.domain.LoginUser;
import com.example.demo.form.AddItemToShoppingCartForm;
import com.example.demo.service.AddItemToShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * ショッピングカートに商品を追加するコントローラー.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class AddItemToShoppingCartController {
    @Autowired
    private AddItemToShoppingCartService addItemToShoppingCartService;

    @Autowired
    private HttpSession session;

    /**
     * ショッピングカートに商品を追加します.
     *
     * @param form フォーム
     * @return ショッピングカートの中身を表示
     */
    @RequestMapping("/addItemToShoppingCart")
    public String addItemToShoppingCart(AddItemToShoppingCartForm form, @AuthenticationPrincipal LoginUser loginUser) {
        Integer userId;
        try {
            userId = loginUser.getUser().getId();
        } catch (NullPointerException exception) {
            userId = session.getId().hashCode();
        }
        addItemToShoppingCartService.addItemToShoppingCart(form, userId);
        return "redirect:/displayTheItemOfShoppingCart";
    }
}
