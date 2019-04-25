package com.ServerLocal.service;


import com.ServerLocal.model.Graduation_goods_rfid;

import java.util.List;

public interface IgoodsRFIDService {
    public List<Graduation_goods_rfid> selectGoodsrfid();
    public boolean delGoodsrfid(String RFID);
}
