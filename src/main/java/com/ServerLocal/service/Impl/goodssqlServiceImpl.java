package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IGoodssqlDao;
import com.ServerLocal.model.Graduation_goods_rfid;
import com.ServerLocal.model.Graduation_goods_sql;
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
    public Graduation_goods_sql getGoodssql(String goods_ID) {
        return goodssqlDao.getGoodssql(goods_ID);
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

    @Override
    public boolean addGoodsRFID(Graduation_goods_rfid goods_rfid) {
        return goodssqlDao.addGoodsRFID(goods_rfid);
    }

    @Override
    public boolean updateGoodsprice(Graduation_goods_sql goods_sql) {
        int i=Integer.parseInt(goods_sql.getGoods_Rquantity());
        i=i-1;
        goods_sql.setGoods_Rquantity(Integer.toString(i));
        return goodssqlDao.updateGoodssql(goods_sql);
    }
}
