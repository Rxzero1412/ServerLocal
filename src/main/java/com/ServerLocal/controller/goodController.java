package com.ServerLocal.controller;

import com.ServerLocal.model.Graduation_goods_sql;
import com.ServerLocal.service.IgoodssqlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by 83541 on 2018/12/15.
 */
@Controller
public class goodController  {
    @Resource
    private IgoodssqlService goodssqlService;

    /**
     * 显示商品种类信息
     * */
    @RequestMapping("/showgoodssql.do")
    @ResponseBody
    public ModelAndView showgoodssql(Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../showgoodssql");
        List<Graduation_goods_sql> listgoodssql=goodssqlService.selectGoodssql();
        System.out.println("showgoodssql");
        model.addAttribute("listgoodssql", listgoodssql);
        return retMap;
    }

    /**
     * 添加商品信息
     * */
    @RequestMapping("/addgoodssql.do")
    @ResponseBody
    public ModelAndView addgoodssql(Model model,
                                    HttpServletRequest request,
                                    String goods_name,
                                    String goods_cost,
                                    String goods_price,
                                    String goods_ID,
                                    String goods_Rquantity) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        Graduation_goods_sql gsql=new Graduation_goods_sql();
        if(goods_ID.equals("null")){
            Date date = new Date();
            gsql.setGoods_ID(Long.toString(date.getTime()));
            gsql.setGoods_name(goods_name);
            gsql.setGoods_cost(goods_cost);
            gsql.setGoods_price(goods_price);
            gsql.setGoods_Rquantity("0");
            goodssqlService.addGoodssql(gsql);
        }else {
            gsql.setGoods_ID(goods_ID);
            gsql.setGoods_name(goods_name);
            gsql.setGoods_cost(goods_cost);
            gsql.setGoods_price(goods_price);
            gsql.setGoods_Rquantity(goods_Rquantity);
            goodssqlService.updateGoodssql(gsql);
        }
        retMap.setViewName("redirect:/showgoodssql.do");
        return retMap;
    }
    /**
     * 删除商品信息
     * */
    @RequestMapping("/delgoodssql.do")
    @ResponseBody
    public ModelAndView delgoodssql(Model model,
                                    HttpServletRequest request,
                                    String goods_ID) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        goodssqlService.delGoodssql(goods_ID);
        retMap.setViewName("redirect:/showgoodssql.do");
        return retMap;
    }


}
