package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IEquipmentDao;
import com.ServerLocal.dao.IUserDao;
import com.ServerLocal.model.User;
import com.ServerLocal.model.equipment;
import com.ServerLocal.service.IUserService;
import com.ServerLocal.service.IequipmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("equipmentService")
public class equipmentServiceImpl implements IequipmentService {

    @Resource
    private IEquipmentDao equipmentDao;
    @Override
    public List<equipment> selectequipment() {
        return equipmentDao.selectequipment();
    }

    @Override
    public boolean delequipment(String id) {
        return equipmentDao.delequipment(id);
    }


}
