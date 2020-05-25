package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.domain.LoginUser;
import com.example.demo.form.DisplayItemListForm;
import com.example.demo.service.DisplayItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    // 1ページに表示する商品数は6個
    private static final int VIEW_SIZE = 6;

    @ModelAttribute
    public DisplayItemListForm setUpDisplayItemListForm() {
        return new DisplayItemListForm();
    }

    /**
     * 商品一覧画面を表示します.
     *
     * @param model     モデル
     * @param page      出力したいページ数
     * @param form      フォーム
     * @return 商品一覧画面
     */
    @RequestMapping("/")
    public String displayItemList(@AuthenticationPrincipal LoginUser user, Model model, Integer page, DisplayItemListForm form) {
        String userName;
        try {
            userName = user.getUser().getName();
        } catch (NullPointerException exception) {
            userName = "ゲスト";
        }
        model.addAttribute("userName", userName);
        // ページング機能追加
        if (page == null) {
            // 初期値を1ページ目にする
            page = 1;
        }
        String sort = null;
        String turn = null;
        try {
            if (form.getSort() == 1) {
                sort = "price_m";
                turn = "ASC";
            } else if (form.getSort() == 2) {
                sort = "price_m";
                turn = "DESC";
            }
        } catch (NullPointerException exception) {
            sort = "id";
            turn = "ASC";
        }
        List<Item> itemList = displayItemListService.displayItemList(form.getName(), sort, turn);
        // ページングの数字からも検索できるように検索文字列をスコープに格納しておく.
        model.addAttribute("searchItemName", form.getName());
        // 表示させたいページ数、ページサイズ、商品リストを渡し1ページに表示させる商品リストを絞り込み
        Page<Item> itemPage = displayItemListService.displayListPaging(page, VIEW_SIZE, itemList);
        model.addAttribute("itemPage", itemPage);
        // ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
        List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
        model.addAttribute("pageNumbers", pageNumbers);
        // オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
        StringBuilder itemListForAutoComplete = displayItemListService.getItemListForAutocomplete(itemList);
        model.addAttribute("itemListForAutoComplete", itemListForAutoComplete);
        return "/item_list_pizza";
    }

    /**
     * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
     *
     * @param model    モデル
     * @param itemPage ページング情報
     */
    private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
        int totalPages = itemPage.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
        }
        return pageNumbers;
    }
}