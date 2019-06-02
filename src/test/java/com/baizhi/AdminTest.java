package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)  //在工厂环境下启动测试
@RunWith (SpringRunner.class)
@SpringBootTest
public class AdminTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void testLogin(){
        Admin aa =  adminDao.selectByName ("aa");
        System.out.println (aa);
    }

}
