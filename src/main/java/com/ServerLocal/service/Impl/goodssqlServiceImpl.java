package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IEquipmentDao;
import com.ServerLocal.dao.IGoodssqlDao;
import com.ServerLocal.model.Graduation_goods_sql;
import com.ServerLocal.model.equipment;
import com.ServerLocal.service.IequipmentService;
import com.ServerLocal.service.IgoodssqlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodssqlService")
public class goodssqlServiceImpl implements IgoodssqlService {

    @Resource
    private IGoodssqlDao goodssqlDao;

    @Override
    public List<Graduation_goods_sql> selectGoodssql() {
        return goodssqlDao.selectGoodssql();
    }

    @Override
    public boolean delGoodssql(String goods_ID) {
        return goodssqlDao.delGoodssql(goods_ID);
    }

    @Override
    public boolean addGoodssql(Graduation_goods_sql goods_sql) {
        return goodssqlDao.addGoodssql(goods_sql);
    }

    @Override
    public boolean updateGoodssql(Graduation_goods_sql goods_sql) {
        return goodssqlDao.updateGoodssql(goods_sql);
    }
}
