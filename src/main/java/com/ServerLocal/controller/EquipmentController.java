package com.ServerLocal.controller;

import com.ServerLocal.model.equipment;
import com.ServerLocal.service.IequipmentService;
import com.ServerLocal.util.com;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class EquipmentController {
    @Resource
    private IequipmentService equipmentService;

    /**
     * 读卡器设置
     * */
    @RequestMapping("/cardreadset.do")
    @ResponseBody
    public ModelAndView cardreadset(Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../cardreadset");
        List<equipment> Listequipment=equipmentService.selectequipment();
        com c=new com();
        List<equipment> equipmentlist=new ArrayList<>();
        List<String> comlist=c.listPorts();
        for (int i=0;i<comlist.size();i++){
            String comname=comlist.get(i);
            equipment e=new equipment();
            e.setCom(comname);
            equipmentlist.add(e);
            System.out.println("port name :"+e);
        }
        List<equipment> equipmentlists=new ArrayList<>();
        for (int i=0;i<Listequipment.size();i++){
            equipment e=Listequipment.get(i);
            if (!e.getCom().contains("COM"));
            else equipmentlists.add(e);
        }
        model.addAttribute("list", equipmentlist);
        model.addAttribute("Listequipment", equipmentlists);
        return retMap;
    }

    /**
     * 监控设备设置
     * */
    @RequestMapping("/monitorset.do")
    @ResponseBody
    public ModelAndView monitorset(Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        List<equipment> Listequipment=equipmentService.selectequipment();
        retMap.setViewName("../../monitorset");
        List<equipment> equipmentlists=new ArrayList<>();
        for (int i=0;i<Listequipment.size();i++){
            equipment e=Listequipment.get(i);
            if (e.getCom().contains("COM")||!e.getName().contains("."));
            else equipmentlists.add(e);
        }
        model.addAttribute("Listip", equipmentlists);
        return retMap;
    }


    @RequestMapping("/motion.do")
    @ResponseBody
    public String motion(Model model,HttpServletRequest request) {
        List<equipment> Listequipment=equipmentService.selectequipment();
        List<equipment> equipmentlists=new ArrayList<>();
        for (int i=0;i<Listequipment.size();i++){
            equipment e=Listequipment.get(i);
            if (e.getCom().contains("COM")||!e.getName().contains("."));
            else{
                if (e.getStatus().equals("1")) equipmentlists.add(e);
            }
        }

        String restr="404";
        if(equipmentlists.size()!=0){
            equipment re=equipmentlists.get(0);
            restr="http://"+re.getName()+":"+re.getCom();
        }
        return restr;
    }

    /**
     * 删除设备
     * */
    @RequestMapping("/equipmentdel.do")
    @ResponseBody
    public ModelAndView equipmentdel(String temp,String id,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        equipmentService.delequipment(id);
        if(temp.equals("0")) retMap.setViewName("redirect:/cardreadset.do");
        else  retMap.setViewName("redirect:/monitorset.do");
        return retMap;
    }

    /**
     * 添加设备
     * */
    @RequestMapping("/equipmentadd.do")
    @ResponseBody
    public ModelAndView equipmentadd(String temp,String name,String com,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        List<equipment> Listequipment=equipmentService.selectequipment();
        if(temp.equals("0")) retMap.setViewName("redirect:/cardreadset.do");
        else  retMap.setViewName("redirect:/monitorset.do");
        equipment e=new equipment();
        e.setCom(com);
        e.setName(name);
        Date date = new Date();
        e.setId(Long.toString(date.getTime()));
        e.setStatus("0");
        equipmentService.addequipment(e);
        return retMap;
    }

    /**
     * 编辑读卡器
     * */
    @RequestMapping("/equipmentupdate.do")
    @ResponseBody
    public ModelAndView equipmentupdate(String temp,String id,String status,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        if(temp.equals("0")) retMap.setViewName("redirect:/cardreadset.do");
        else  retMap.setViewName("redirect:/monitorset.do");
        equipment e=new equipment();
        e.setId(id);
        if(status.equals("0")) e.setStatus("1");
        else e.setStatus("0");
        equipmentService.updateequipment(e);
        return retMap;
    }
}
