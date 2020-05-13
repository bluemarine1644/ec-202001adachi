package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.form.RegisterUserForm;
import com.example.demo.service.RegisterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ユーザを登録するコントローラ.
 *
 * @author sota_adachi
 */
@Controller
@RequestMapping("/")
public class RegisterUserController {
    @Autowired
    private RegisterUserService registerUserService;

    @ModelAttribute
    public RegisterUserForm setUpRegisterUserForm() {
        return new RegisterUserForm();
    }

    @RequestMapping("/toInsert")
    public String toInsert() {
        return "register_user";
    }

    @RequestMapping("/insert")
    public String insert(@Validated RegisterUserForm form, BindingResult result) {
        System.out.println(form.toString());
        // パスワードと確認パスワードが合致していない場合エラー
        if (!form.getPassword().equals(form.getConfirmationPassword())) {
            result.rejectValue("password", "", "！パスワードが一致していません！");
        }
        // メールアドレスが既に登録されている場合エラー
        if (registerUserService.findUserByEmail(form.getEmail()) != null) {
            result.rejectValue("email", "", "！そのメールアドレスは既に登録されています！");
        }
        // エラーがあれば入力画面に戻す
        if (result.hasErrors()) {
            return toInsert();
        }
        // フォームの値をドメインにコピーする
        User user = new User();
        BeanUtils.copyProperties(form, user);
        registerUserService.registerUser(user);

        return "redirect:/toLogin";
    }
}
