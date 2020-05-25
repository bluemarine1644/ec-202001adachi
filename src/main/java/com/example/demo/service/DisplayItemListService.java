package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 商品一覧を表示するサービス.
 *
 * @author sota_adachi
 */
@Service
@Transactional
public class DisplayItemListService {

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 商品一覧を表示します.
     *
     * @param name 商品名
     * @return 商品一覧情報
     */
    public List<Item> displayItemList(String name, String sort, String turn) {
        return itemMapper.findByItemNameAndSortAndTurn(name, sort, turn);
    }

    /**
     * ページング用メソッド.
     *
     * @param page     表示させたいページ数
     * @param size     1ページに表示させたい商品数
     * @param itemList 絞り込み対象リスト
     * @return 1ページに表示されるサイズ分の商品一覧情報
     */
    public Page<Item> displayListPaging(Integer page, Integer size, List<Item> itemList) {
        // 表示させたいページ数を-1しなければうまく動かない
        page--;

        // どの従業員から表示させるかと言うカウント値
        int startItemCount = page * size;

        // 絞り込んだ後の商品リストが入る変数
        List<Item> list;

        if (itemList.size() < startItemCount) {

            // (ありえないが)もし表示させたい商品カウントがサイズよりも大きい場合は空のリストを返す
            list = Collections.emptyList();

        } else {

            // 該当ページに表示させる商品一覧を作成
            int toIndex = Math.min(startItemCount + size, itemList.size());
            list = itemList.subList(startItemCount, toIndex);

        }

        // 上記で作成した該当ページに表示させる従業員一覧をページングできる形に変換して返す
        return new PageImpl<>(list, PageRequest.of(page, size), itemList.size());
    }

    /**
     * オートコンプリート用にJavaScriptの配列の中身を文字列で作ります.
     *
     * @param itemList 従業員一覧
     * @return オートコンプリート用JavaScriptの配列の文字列
     */
    public StringBuilder getItemListForAutocomplete(List<Item> itemList) {
        StringBuilder itemListForAutocomplete = new StringBuilder();
        for (int i = 0; i < itemList.size(); i++) {
            if (i != 0) {
                itemListForAutocomplete.append(",");
            }
            Item item = itemList.get(i);
            itemListForAutocomplete.append("\"");
            itemListForAutocomplete.append(item.getId());
            itemListForAutocomplete.append("\"");
        }
        return itemListForAutocomplete;
    }

}
