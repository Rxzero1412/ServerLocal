package com.ServerLocal.dao;

import com.ServerLocal.model.Graduation_user;
import com.ServerLocal.model.User;

import java.util.List;

public interface IUserDao {

    public User selectByName(String username);
    public List<Graduation_user> selectAlluser();
    public boolean updatepassword(User user);

}
