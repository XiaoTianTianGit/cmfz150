package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.Map;

//定义管理员service 的 接口
public interface AdminService {
    //登录  根据名称查询

    public Map AdminLogin(String usernamne, String password, String enCode,HttpSession session);


}
