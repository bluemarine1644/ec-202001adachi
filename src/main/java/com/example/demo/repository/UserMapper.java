package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * userテーブルを操作するマッパー.
 *
 * @author sota_adachi
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * ユーザ情報を登録します.
     *
     * @param user ユーザ情報
     */
    @Insert("INSERT INTO users(*) VALUES(#{name}, #{email}, #{password}, #{zipcode}, #{address}, #{telephone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    /**
     * メールアドレスからユーザ情報を取得します.
     *
     * @param email メールアドレス
     * @return ユーザ情報
     */
    @Select("SELECT * FROM users WHERE email = #{email}")
    List<User> select(String email);
}
