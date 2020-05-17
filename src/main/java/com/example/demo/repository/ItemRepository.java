package com.example.demo.repository;

import com.example.demo.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * itemsテーブルを操作するリポジトリ.
 *
 * @author sota_adachi
 */
@Repository
@Mapper
public interface ItemRepository {
    /**
     * 商品一覧情報を検索します.
     *
     * @param name 商品名
     * @param sort 並び替え
     * @return 商品一覧情報
     */
    @SelectProvider(type = ItemSqlProvider.class, method = "selectItemList")
    List<Item> selectItemList(String name, String sort, String turn);

    /**
     * 商品情報を検索します.
     *
     * @param id 商品ID
     * @return 商品情報
     */
    @Select("SELECT * FROM items WHERE id = #{id}")
    Item loadItem(Integer id);

    class ItemSqlProvider {

        public String selectItemList(String name, String sort, String turn) {
            System.out.println("sort[" + sort + "], turn[" + turn + "]");
            return new SQL() {{
                SELECT("*");
                FROM("items");
                if (!StringUtils.isEmpty(name)) {
                    WHERE("(name) LIKE #{name}");
                }
                ORDER_BY("${sort} ${turn}");
            }}.toString();
        }
    }
}
