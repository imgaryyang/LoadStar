package com.ciel.pocket.auth.service;

import com.ciel.pocket.auth.domain.User;

import java.util.Optional;

/**
 * @Author Ciel Qian
 * @CreateDate 2018/8/23
 * @Comment
 */
public interface UserService {
    void createUser(User user);

    void deleteUser(String username);

    Optional<User> queryUser(String username);
}
