package com.waxsb.service;

import com.waxsb.model.User;

public interface UserService {

    boolean register(User user);
    User login(User user);
    void updateUser(User user);
    void changePassword(User user, String newPassword);
}
