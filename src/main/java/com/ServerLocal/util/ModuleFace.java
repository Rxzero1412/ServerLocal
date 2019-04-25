package com.ServerLocal.util;

import com.ServerLocal.model.Graduation_goods_sql;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 83541 on 2019/4/18.
 */
public class ModuleFace extends Thread  {
    public static Map<String,Graduation_goods_sql> map=new HashMap<>();
    public static int mapsize=0;
    public static double money=0.00;
}
