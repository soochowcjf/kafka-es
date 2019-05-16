package com.raiyi.es.service;

import com.raiyi.es.entity.User;

import java.util.List;

/**
 * @author chenjinfeng
 * @create 14:48 2019/5/16
 * @desc
 **/
public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(String userId);

    User addNewUser(User user);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);
}
