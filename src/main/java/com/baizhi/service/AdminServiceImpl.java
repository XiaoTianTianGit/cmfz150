package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED ,readOnly = true)
    public Map AdminLogin(String username, String password,String enCode, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object> ();
        Admin admin = adminDao.selectByName (username);
        //获取系统生成的验证码
        String securityCode = (String) session.getAttribute ("securityCode");
        if (securityCode.equals (enCode)){
            if (admin!=null){
                if (admin.getPassword ().equals (password)){
                    //将验证成功的用户存入到 session中
                    session.setAttribute ("admin",admin);
                    map.put ("message","ok");
                }else {
                    map.put ("message","密码错误");
                }
            }else {
                map.put ("message","用户不存在");
            }
        }else {
            map.put ("message","验证码错误");
        }
        return map;
    }
}
