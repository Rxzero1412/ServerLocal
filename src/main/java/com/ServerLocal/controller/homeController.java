package com.ServerLocal.controller;

import com.ServerLocal.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class homeController {
    //初始化
    @RequestMapping("/home.do")
    @ResponseBody
    public ModelAndView home(User user,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        retMap.setViewName("../../home");
        return retMap;
    }

}