package com.ServerLocal.controller;

import com.ServerLocal.model.User;
import com.ServerLocal.model.equipment;
import com.ServerLocal.service.IUserService;
import com.ServerLocal.service.IequipmentService;
import com.ServerLocal.util.Utils;
import com.ServerLocal.util.com;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EquipmentController {
    @Resource
    private IequipmentService equipmentService;

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
        model.addAttribute("list", equipmentlist);
        model.addAttribute("Listequipment", Listequipment);
        return retMap;
    }

    @RequestMapping("/cardreaddel.do")
    @ResponseBody
    public ModelAndView cardreaddel(String id,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        equipmentService.delequipment(id);
        retMap.setViewName("redirect:/cardreadset.do");
        return retMap;
    }
}
