package com.example.demo.controller;

import com.example.demo.service.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザログインをするコントローラ.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class UserLoginController {
    @Autowired
    private UserLoginServiceImpl userLoginService;

    @RequestMapping("/toLogin")
    public String toLogin(Model model, String error) {
        System.err.println("login error" + error);
        if (error != null) {
            System.err.println("login failed");
            model.addAttribute("errorMessage", "！メールアドレスまたはパスワードが不正です！");
        }
        return "/login";
    }
}
