package com.baizhi.service;
import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    //添加一条数据
    @Override
    public String addBanner(Banner banner) {
        //处理id  uuid
        banner.setId (UUID.randomUUID ().toString ());
        //上传时间
        banner.setCreatetime (new Date ());
        //状态 默认展示
       // banner.setStatus ("展示");
        //System.out.println ("传入daobanner======"+banner);

        int i = bannerMapper.insertSelective (banner);
        return banner.getId ();
    }
    @Override
    public String updateBanner(Banner banner) {

        bannerMapper.updateByPrimaryKeySelective (banner);
        return banner.getId ();
    }
    @Override
    public void deteleBanner(String[] ids) {
        bannerMapper.deleteByPrimaryKey (ids);
    }

    @Override
    public Banner findByIdBanner(String id) {
        Banner banner = bannerMapper.selectByPrimaryKey (id);
        return banner;
    }

    @Override
    public Map<String,Object> findAllBannerPage(Integer PageNumber, Integer rows) {
        //准备返回客户端的数据 用map集合存储
        HashMap<String, Object> map = new HashMap<> ();
        // 1.将当前页号存入 map 集合
        map.put ("page",PageNumber);
       //2.将总条数存入map 集合中
          //调用Dao方法 获取总条数
        int count = bannerMapper.conuntBanner ();
        map.put ("records",count);
       //3.将总页数 存入 map 中
         //初始化总页数
        Integer pageCount = 0;
        //计算总页数
        if (count%rows!=0){
        pageCount = count/rows+1;
        }else {
            pageCount = count/rows;
        }
        //System.out.println ("总页数==="+pageCount);
          //将计算获得的总页数 存储到 map 中
        map.put ("total",pageCount);
          //计算起始页号
       Integer beginRows = (PageNumber-1)*rows;
        //查询结果
        List<Banner> banners = bannerMapper.selectBannerPage (beginRows, rows);
        //System.out.println ("ffffffff"+banners);

        //将查询的结果存储到 map 集合 中
        map.put ("rows",banners);
        return map;
    }

    //计算总条数
    @Override
    public int countBannerService() {
        int i = bannerMapper.conuntBanner ();
        return i;
    }

    @Override
    public void updateImgUrlService(MultipartFile picpath, HttpSession session, String bannerId) {

        System.out.println ("bannerId=="+bannerId);
        System.out.println ("picpath=========="+picpath);
        System.out.println ("picpath999"+picpath.getOriginalFilename ());
        String filename = picpath.getOriginalFilename ();
        if ("".equals (filename)|| filename==null){
            System.out.println ("lllllllllllll");

            //System.out.println (banner+"ggggggggg");



        }else {
            System.out.println ("-0------------");
            //获取图片路径
            String realPath = session.getServletContext ().getRealPath ("/upload/img");
            File file = new File (realPath);
            //判断上传的文件夹是否存在
            if (!file.exists ()){
                file.mkdirs ();
            }

            String originalFilename = picpath.getOriginalFilename ();
            String str = new Date ().getTime () + "_" + originalFilename;
            try {
                picpath.transferTo (new File (realPath,str));
                Banner banner = new Banner ();
                banner.setId (bannerId);
                banner.setPicpath (str);
                System.out.println (banner+"ggggggggg");
                bannerMapper.updateByPrimaryKeySelective (banner);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }


        // Map<String, Object> map = new HashMap<String, Object>();

    }
    //批量插入
    @Override
    public void addAllBannerService(List<Banner> list) {
        bannerMapper.addAllBanner (list);
    }
}
