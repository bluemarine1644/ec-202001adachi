package com.example.demo.repository;

import com.example.demo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * itemsテーブルを操作するリポジトリ.
 *
 * @author sota_adachi
 */
@Repository
public class ItemRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    /**
     * itemsテーブルを操作するローマッパー.
     */
    private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPriceM(rs.getInt("price_m"));
        item.setPriceL(rs.getInt("price_l"));
        item.setImagePath(rs.getString("image_path"));
        item.setDeleted(rs.getBoolean("deleted"));
        return item;
    };

    /**
     * 商品を名前順で（曖昧）検索します.
     *
     * @param name 商品名
     * @return 商品一覧情報
     */
    public List<Item> selectItems(String name) {
        String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ";
        if (!StringUtils.isEmpty(name)) {
            sql += "WHERE (name) LIKE :name ";
        }
        sql += "ORDER BY name";
        SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
        return template.query(sql, param, ITEM_ROW_MAPPER);
    }
}
