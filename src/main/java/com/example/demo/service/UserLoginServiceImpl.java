package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;

/**
 * ログイン後の管理者情報に権限情報を付与するサービスクラス.
 *
 * @author sota_adachi
 */
@Service
public class UserLoginServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String) DBから検索をし、ログイン情報を構成して返す。
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> userList = userMapper.select(email);
        User user = null;
        if (userList.size() != 0) {
            user = userList.get(0);
        }
        if (user == null) {
            throw new UsernameNotFoundException("そのEmailは登録されていません。");
        }
        // 権限付与の例
        Collection<GrantedAuthority> authoritiyList = new ArrayList<>();
        authoritiyList.add(new SimpleGrantedAuthority("ROLE_USER")); // ユーザ権限付与
//			if(member.isAdmin()) {
//				authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
//			}
        return new LoginUser(user, authoritiyList);
    }
}
