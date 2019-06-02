package com.baizhi;

import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@RunWith(SpringRunner.class)  //在工厂环境下启动测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerTest {
   @Autowired
   private BannerMapper bannerMapper;

   @Autowired
    BannerService bannerService;
   //添加一条数据
    @Test
    public void test1(){
        Banner banner = new Banner ();
        banner.setId ("1");
        banner.setCreatetime (new Date ());
        banner.setDescription ("ddd");
        banner.setPicpath ("cccc");
        banner.setPicname ("cccff");
        banner.setStatus ("xxxx");

        // banner.setId (1,);
        bannerService.addBanner (banner);


    }
    @Test
    public void test2(){
        Banner banner = new Banner ();
        banner.setId ("fdc06be4-a3ab-43bf-8c3b-50a812a55c19");
        banner.setPicpath ("fffffff");
        bannerMapper.updateImgUrl (banner);
    }





}
