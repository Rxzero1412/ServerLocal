package com.ServerLocal.service;


import com.ServerLocal.model.Graduation_Historical;

import java.util.Map;

public interface IgoodsHistoricalService {
    public boolean addGoodsHistorical(Graduation_Historical goods_Historical);
    public Map<String,Double> selectGoodsHistorical();
}
