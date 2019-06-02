package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
@Controller
@RequestMapping("/Admin")
@SessionAttributes(value = {"admin"})
public class AdminController {

    @Autowired
    private AdminService adminService;

    //登录

    @RequestMapping("/adminLogin.do")
    public @ResponseBody Map adminLogin(Model model, String username, String password,String enCode, HttpSession session){
        Map map = adminService.AdminLogin (username, password,enCode,session);
        Admin admin = (Admin) session.getAttribute ("admin");
        model.addAttribute ("admin",admin);
        System.out.println (admin);
        return map;
    }

//安全退出
    @RequestMapping("/secedeLogin.do")
    public String secedeLogin(HttpSession session,HttpServletResponse response){
        //销毁session
       session.invalidate ();
       return "redirect:/login/login.jsp";

    }
}
