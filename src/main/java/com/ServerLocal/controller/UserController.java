package com.ServerLocal.controller;

import com.ServerLocal.model.Graduation_user;
import com.ServerLocal.model.User;
import com.ServerLocal.service.IUserService;
import com.ServerLocal.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //更新管理员密码
    @RequestMapping("/updatepassword.do")
    @ResponseBody
    public String updatepassword(User user, Model model, HttpServletRequest request) {
        String Md5= null;
        try {
            Md5 = Utils.EncoderByMd5(user.getPassword());
            user.setPassword(Md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            boolean b=userService.updatepassword(user);
            return String.valueOf(b);
        }
    }

    //用户id列表
    @RequestMapping("/selectuser.do")
    @ResponseBody
    public String selectuser( Model model, HttpServletRequest request) {
        List<Graduation_user> list=userService.selectAlluser();
        String re = list.get(0).getUserid();
        for (int i=1;i<list.size();i++){
            re+=","+list.get(i).getUserid();
        }
        return re;
    }


    /**
     * 用户信息管理
     * */
    @RequestMapping("/showuser.do")
    @ResponseBody
    public ModelAndView showuser(Model model,HttpServletRequest request) {
        ModelAndView retMap = new ModelAndView();  //返回新的ModelAndView
        List<Graduation_user> list=userService.selectAlluser();
        retMap.setViewName("../../showuser");
        model.addAttribute("listuser", list);
        return retMap;
    }

}
