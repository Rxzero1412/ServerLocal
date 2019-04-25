package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IUserDao;
import com.ServerLocal.model.Graduation_user;
import com.ServerLocal.model.User;
import com.ServerLocal.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public Graduation_user selectUserid(String userid) {
        return userDao.selectUserid(userid);
    }

    public boolean login(String username, String password){

        User user = userDao.selectByName(username);
        if (user != null) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }

    @Override
    public List<Graduation_user> selectAlluser() {
        return userDao.selectAlluser();
    }

    @Override
    public boolean updatepassword(User user) {
        return userDao.updatepassword(user);
    }
}
