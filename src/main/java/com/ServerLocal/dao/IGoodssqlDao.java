package com.ServerLocal.dao;

import com.ServerLocal.model.Graduation_goods_sql;

import java.util.List;

public interface IGoodssqlDao {
    public List<Graduation_goods_sql> selectGoodssql();
    public boolean delGoodssql(String goods_ID);
    public boolean addGoodssql(Graduation_goods_sql goods_sql);
    public boolean updateGoodssql(Graduation_goods_sql goods_sql);

}