package com.ServerLocal.dao;

import com.ServerLocal.model.User;

public interface IUserDao {

    public User selectByName(String username);

}
