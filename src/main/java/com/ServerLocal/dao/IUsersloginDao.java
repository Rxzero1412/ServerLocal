package com.ServerLocal.dao;

import com.ServerLocal.model.User;
import com.ServerLocal.model.userslogin;

import java.util.List;

public interface IUsersloginDao {

    public userslogin selectuserslogin(String username);
    public List<userslogin> selectuserslogins();
    public boolean adduserslogin(userslogin ul);

}
