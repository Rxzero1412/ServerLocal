package com.ServerLocal.controller;

import javax.servlet.http.HttpServletRequest;

import com.ServerLocal.model.User;
import com.ServerLocal.service.IUserService;
import com.ServerLocal.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    /**
     * 登陆
     * */
    @RequestMapping("/login.do")
    @ResponseBody
    public ModelAndView loginIn(User user,Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        Map<String, Object> resultMap = new HashMap<String, Object>();
        boolean flag=false;
        if((user.getUsername()  == null)&&(user.getPassword() == null)){
            retMap.setViewName("../../login");
        }else {
            String Md5= null;
            try {
                Md5 = Utils.EncoderByMd5(user.getPassword());
                System.out.println(Md5);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            flag = userService.login(user.getUsername(),Md5);
            if(flag){
                resultMap.put("result", 1);
                retMap=new ModelAndView("redirect:/home.do?username="+user.getUsername());
            }else{
                if((user.getUsername()  == null)&&(user.getPassword() == null)){
                    retMap.setViewName("../../login");
                }else{
                    resultMap.put("result", 3);
                    resultMap.put("errorMsg", "登录失败！账号或密码错误！");
                    model.addAttribute("errorMsg", "登录失败！账号或密码错误！");
                    retMap.setViewName("../../login");
                }
            }
        }
        return retMap;
    }
}
