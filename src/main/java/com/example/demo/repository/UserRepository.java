package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author sota_adachi
 *
 */
@Repository
public class UserRepository {
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * メールアドレスからユーザ情報を取得します.
	 * 
	 * @param email メールアドレス
	 * @return ユーザ情報 存在しない場合はnullを返します
	 */
	public User findByEmail(String email) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE :email ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
}
