package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IUserDao;
import com.ServerLocal.model.User;
import com.ServerLocal.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public boolean login(String username, String password){

        User user = userDao.selectByName(username);
        if (user != null) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
