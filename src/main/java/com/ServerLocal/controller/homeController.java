package com.ServerLocal.controller;

import com.ServerLocal.model.Graduation_goods_sql;
import com.ServerLocal.service.IgoodsHistoricalService;
import com.ServerLocal.service.IgoodssqlService;
import com.ServerLocal.util.CPU;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class homeController {
    @Resource
    private IgoodssqlService goodssqlService;

    @Resource
    private IgoodsHistoricalService goodsHistoricalService;

    /**
     * 首页
     * */
    @RequestMapping("/home.do")
    @ResponseBody
    public ModelAndView home(String username,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../home");
        model.addAttribute("username", username);
        return retMap;
    }

    /**
     * 首页
     * */
    @RequestMapping("/homeimg.do")
    @ResponseBody
    public ModelAndView homeimg(String username,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../homeimg");
        List<Graduation_goods_sql> listgoodssql=goodssqlService.selectGoodssql();
        Map<String,Integer> regoods=new HashMap<>();
        for (int i=0;i<listgoodssql.size();i++){
            Graduation_goods_sql g=listgoodssql.get(i);
            regoods.put(g.getGoods_name(),Integer.valueOf(g.getGoods_Rquantity()));
        }
        Map<String,Double> remaps=goodsHistoricalService.selectGoodsHistorical();
        model.addAttribute("remaps", new JSONObject(remaps));
        model.addAttribute("regoods", new JSONObject(regoods));
        return retMap;
    }

    /**
     * 获取CPU使用率
     * */
    @RequestMapping("/getcpu.do")
    @ResponseBody
    public double getcpu(HttpServletRequest request){
        if(!new CPU().cpusboolean){
            new CPU().cpusboolean=true;
            new CPU().run();
        }
        double re=(double)Math.round(new CPU().cpus*100);
        return re;
    }
}
