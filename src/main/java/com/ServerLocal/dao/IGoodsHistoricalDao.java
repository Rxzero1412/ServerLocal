package com.ServerLocal.dao;

import com.ServerLocal.model.Graduation_Historical;

import java.util.List;

public interface IGoodsHistoricalDao {
    public boolean addGoodsHistorical(Graduation_Historical goods_Historical);
    public List<Graduation_Historical> selectGoodsHistorical();


}
