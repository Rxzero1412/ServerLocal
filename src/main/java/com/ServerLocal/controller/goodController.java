package com.ServerLocal.controller;

import com.ServerLocal.model.*;
import com.ServerLocal.service.*;
import com.ServerLocal.util.ModuleFace;
import com.ServerLocal.util.com;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 83541 on 2018/12/15.
 */
@Controller
public class goodController  {
    static com c;
    @Resource
    private IgoodssqlService goodssqlService;
    @Resource
    private IequipmentService equipmentService;

    @Resource
    private IgoodsRFIDService goodsRFIDService;

    @Resource
    private IgoodsHistoricalService goodsHistoricalService;

    @Resource
    private IUserService userService;

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

    /**
     * add goods RFID
     * */
    @RequestMapping("/addgoodsRFID.do")
    @ResponseBody
    public ModelAndView addgoodsRFID(String goods_ID,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../addgoodsRFID");
        new com().list=new ArrayList<String>();
        List<String> docomlist=new ArrayList<>();
        List<equipment> Listequipment=equipmentService.selectequipment();
        List<String> listcom=new com().listPorts();
        for (int i=0;i<Listequipment.size();i++){
            equipment e=Listequipment.get(i);
            if(listcom.contains(e.getCom())){
                if(e.getStatus().equals("1")){
                    docomlist.add(e.getCom());
                }
            }
        }
        model.addAttribute("docomlist", docomlist);
        model.addAttribute("goods_ID", goods_ID);
        return retMap;
    }



    /**
     * get RFID
     * */
    @RequestMapping("/getRFID.do")
    @ResponseBody
    public List<String> getRFID(Model model,HttpServletRequest request) {
        return new com().list;
    }

    /**
     * open get RFID
     * */
    @RequestMapping("/opengetRFID.do")
    @ResponseBody
    public ModelAndView opengetRFID(Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        List<String> docomlist=new ArrayList<>();
        c=new com();
        List<equipment> Listequipment=equipmentService.selectequipment();
        List<String> listcom=new com().listPorts();
        for (int i=0;i<Listequipment.size();i++){
            equipment e=Listequipment.get(i);
            if(listcom.contains(e.getCom())){
                if(e.getStatus().equals("1")){
                    docomlist.add(e.getCom());
                }
            }
        }
        c.runs(docomlist);
        return retMap;
    }

    /**
     * getRFIDgoods
     * */
    @RequestMapping("/getRFIDgoods.do")
    @ResponseBody
    public ModelAndView getRFIDgoods(Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../getRFIDgoods");
        new ModuleFace().money=0.00;
        new com().list=new ArrayList<>();
        return retMap;
    }

    /**
     * facemodule get RFID goods
     * */
    @RequestMapping("/facegetgoods.do")
    @ResponseBody
    public List<facegoods> facegetgoods(Model model,HttpServletRequest request) {
        new ModuleFace().money=0.00;
        double money=0.00;
        List<facegoods> relist=new ArrayList<>();
        List<Graduation_goods_rfid> goodsrfidlist=goodsRFIDService.selectGoodsrfid();
        if(goodsrfidlist.size()!=new ModuleFace().mapsize){
            Map<String,Graduation_goods_sql> maps=new HashMap<>();
            for (int i=0;i<goodsrfidlist.size();i++){
                Graduation_goods_rfid grfid=goodsrfidlist.get(i);
                Graduation_goods_sql gsql=goodssqlService.getGoodssql(grfid.getGoods_ID());
                maps.put(grfid.getRFID(),gsql);
            }
            new ModuleFace().map.clear();
            new ModuleFace().map.putAll(maps);
            new ModuleFace().mapsize=maps.size();
        }
        Map<String,Graduation_goods_sql> mapss=new HashMap<>();
        mapss.putAll(new ModuleFace().map);
        List<String> rfidlist=new com().list;
        for (int i=0;i<rfidlist.size();i++){
            String s=rfidlist.get(i);
            if(mapss.containsKey(s)){
                Graduation_goods_sql gsss=mapss.get(s);
                facegoods f=new facegoods();
                f.setRFID(s);
                f.setGoods_name(gsss.getGoods_name());
                f.setGoods_price(gsss.getGoods_price());
                relist.add(f);
                money+=Double.valueOf(gsss.getGoods_price());
            }
        }
        new ModuleFace().money=money;
        return relist;
    }

    /**
     * facegetgoodstop
     * */
    @RequestMapping("/facegetmoney.do")
    @ResponseBody
    public String facegetmoney(String userid,Model model,HttpServletRequest request) {
        String restr=String.valueOf(new ModuleFace().money);
        Graduation_user u=userService.selectUserid(userid);
        return restr+"#"+u.getUsername();
    }


    /**
     * facegetgoodstop
     * */
    @RequestMapping("/facegetgoodstop.do")
    @ResponseBody
    public String facegetgoodstop(String userid,Model model,HttpServletRequest request) {
        new com().stops();
        String restr="200";
        List<String> rfidlist=new com().list;
        Map<String,Graduation_goods_sql> mapss=new ModuleFace().map;
        for (int i=0;i<rfidlist.size();i++){
            String s=rfidlist.get(i);
            Graduation_Historical gh=new Graduation_Historical();
            Graduation_goods_sql gsss=mapss.get(s);
            if(gsss!=null){
                mapss.remove(s);
                gh.setUserid(userid);
                gh.setGoods_name(gsss.getGoods_name());
                gh.setGoods_price(gsss.getGoods_price());
                gh.setGoods_count("1");
                gh.setTime(getStringDateShort());
                goodsHistoricalService.addGoodsHistorical(gh);
                goodsRFIDService.delGoodsrfid(s);
                goodssqlService.updateGoodsprice(gsss);
            }
        }
        return restr;
    }

    /**
         * 获取现在时间
         *
         * @return 返回短时间字符串格式yyyy-MM-dd
         */
    public static String getStringDateShort() {
        Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           String dateString = formatter.format(currentTime);
            return dateString;
         }



    /**
     * savegoodsRFID
     * */
    @RequestMapping("/savegoodsRFID.do")
    @ResponseBody
    public ModelAndView savegoodsRFID(String goods_ID,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        Graduation_goods_sql ggs=goodssqlService.getGoodssql(goods_ID);
        List<String> goodsrfidlist=new ArrayList<>();
        goodsrfidlist.addAll(new com().list);
        for (int i=0;i<goodsrfidlist.size();i++){
            Graduation_goods_rfid ggf=new Graduation_goods_rfid();
            ggf.setGoods_ID(goods_ID);
            ggf.setRFID(goodsrfidlist.get(i));
            goodssqlService.addGoodsRFID(ggf);
        }
        int rq=Integer.parseInt(ggs.getGoods_Rquantity())+goodsrfidlist.size();
        ggs.setGoods_Rquantity(String.valueOf(rq));
        goodssqlService.updateGoodssql(ggs);
        new com().stops();
        return retMap;
    }



}
