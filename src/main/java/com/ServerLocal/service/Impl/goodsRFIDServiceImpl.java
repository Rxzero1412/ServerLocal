package com.ServerLocal.service.Impl;

import com.ServerLocal.dao.IGoodsRFIDDao;
import com.ServerLocal.model.Graduation_goods_rfid;
import com.ServerLocal.service.IgoodsRFIDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsRFIDService")
public class goodsRFIDServiceImpl implements IgoodsRFIDService {

    @Resource
    private IGoodsRFIDDao GoodsRFIDDao;


    @Override
    public List<Graduation_goods_rfid> selectGoodsrfid() {
        return GoodsRFIDDao.selectGoodsrfid();
    }

    @Override
    public boolean delGoodsrfid(String RFID) {
        return GoodsRFIDDao.delGoodsrfid(RFID);
    }
}
