package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IGoodsHistoricalDao;
import com.ServerLocal.model.Graduation_Historical;
import com.ServerLocal.service.IgoodsHistoricalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("goodsHistoricalService")
public class goodsHistoricalServiceImpl implements IgoodsHistoricalService {

    @Resource
    private IGoodsHistoricalDao GoodsHistoricalDao;

    @Override
    public boolean addGoodsHistorical(Graduation_Historical goods_Historical) {
        return GoodsHistoricalDao.addGoodsHistorical(goods_Historical);
    }
}
