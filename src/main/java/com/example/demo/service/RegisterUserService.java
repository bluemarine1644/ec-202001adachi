package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ユーザを登録するサービス.
 *
 * @author sota_adachi
 */
@Service
public class RegisterUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * メールアドレスからユーザ情報を取得します.
     *
     * @param email メールアドレス
     * @return ユーザ情報（存在しない場合はnullを返します）
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * ユーザ情報を登録します.
     *
     * @param user ユーザ情報
     */
    public void registerUser(User user) {
        // パスワードをハッシュ化
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.insert(user);
    }
}
