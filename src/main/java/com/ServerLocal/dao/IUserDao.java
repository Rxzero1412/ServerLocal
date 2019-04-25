package com.ServerLocal.dao;

import com.ServerLocal.model.Graduation_user;
import com.ServerLocal.model.User;

import java.util.List;

public interface IUserDao {

    public User selectByName(String username);
    public Graduation_user selectUserid(String userid);
    public List<Graduation_user> selectAlluser();
    public boolean updatepassword(User user);

}
