package com.ServerLocal.controller;

import com.ServerLocal.util.CPU;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class homeController {

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
