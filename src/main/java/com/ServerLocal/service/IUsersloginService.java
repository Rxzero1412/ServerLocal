package com.ServerLocal.service;

import com.ServerLocal.model.userslogin;

import java.util.List;

public interface IUsersloginService {
    public List<userslogin> selectuserslogins();
    public boolean adduserslogin(userslogin ul);

}
