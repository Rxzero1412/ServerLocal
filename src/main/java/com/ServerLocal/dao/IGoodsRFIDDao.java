package com.ServerLocal.dao;

import com.ServerLocal.model.Graduation_goods_rfid;

import java.util.List;

public interface IGoodsRFIDDao {
    public List<Graduation_goods_rfid> selectGoodsrfid();
    public boolean delGoodsrfid(String RFID);


}
