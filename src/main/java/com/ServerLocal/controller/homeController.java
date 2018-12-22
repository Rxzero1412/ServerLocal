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

}
