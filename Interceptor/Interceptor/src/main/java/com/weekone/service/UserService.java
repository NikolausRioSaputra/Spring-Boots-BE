package com.weekone.service;

import com.weekone.model.User;

import java.util.List;

public interface UserService {
    List<User> listAll();

    User get(String userId);

    User save(User user);

    void delete(String userId);

}
