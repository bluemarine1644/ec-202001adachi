package com.example.demo.repository;

import com.example.demo.domain.Topping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * toppingsテーブルを操作するリポジトリ.
 *
 * @author sota_adachi
 */
@Mapper
public interface ToppingRepository {
    /**
     * トッピング情報を検索します.
     *
     * @return トッピング情報
     */
    @Select("SELECT * FROM toppings")
    List<Topping> select();
}
