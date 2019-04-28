package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IGoodsHistoricalDao;
import com.ServerLocal.model.Graduation_Historical;
import com.ServerLocal.service.IgoodsHistoricalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("goodsHistoricalService")
public class goodsHistoricalServiceImpl implements IgoodsHistoricalService {

    @Resource
    private IGoodsHistoricalDao GoodsHistoricalDao;

    @Override
    public boolean addGoodsHistorical(Graduation_Historical goods_Historical) {
        return GoodsHistoricalDao.addGoodsHistorical(goods_Historical);
    }

    @Override
    public Map<String,Double> selectGoodsHistorical() {
        List<Graduation_Historical> G=GoodsHistoricalDao.selectGoodsHistorical();
        Map<String,Double> remap=new HashMap<>();
        for(int i=0;i<G.size();i++){
            Graduation_Historical gs=G.get(i);
            if(remap.containsKey(gs.getTime())){
                Double d=remap.get(gs.getTime());
                d=d+Double.parseDouble(gs.getGoods_price());
                remap.put(gs.getTime(),d);
            }else {
                remap.put(gs.getTime(),Double.parseDouble(gs.getGoods_price()));
            }
        }
        return remap;
    }
}

