package com.ServerLocal.service;

import com.ServerLocal.model.Graduation_user;
import com.ServerLocal.model.User;

import java.util.List;

public interface IUserService {

    public boolean login(String username, String password);
    public List<Graduation_user> selectAlluser();
    public boolean updatepassword(User user);

}
