package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 轮播图 控制层
 * */

@RestController
@RequestMapping("/Banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    //分页展示所有
    @RequestMapping("/showPageBannner.do")
    public @ResponseBody Map<String,Object> showPageBannner(Integer page, Integer rows){
        Map<String, Object> mapBannerPage = bannerService.findAllBannerPage (page,rows);
        return  mapBannerPage;
    }

    // 增删改
    @RequestMapping("/edit.do")
    @ResponseBody // 规定
    public Map<String, Object>  edit(String oper,Banner banner,String[] id){
        Map<String, Object> map = new HashMap<String, Object>();
        //判断是哪个操作 分别调用对应的增删改方法
        if ("add".equals (oper)){
            String s = bannerService.addBanner (banner);
            map.put ("message","添加成功");
            map.put ("bannerId",s);
        }else if("edit".equals (oper)){
            String s = bannerService.updateBanner (banner);
            map.put ("message","修改成功");
            map.put ("bannerId",s);
        }else if ("del".equals (oper)){

            bannerService.deteleBanner (id);
        }
        return map;
    }

    //处理 图片 上传
    @RequestMapping("/uplode.do")
    public void uplode(MultipartFile picpath, HttpSession session,String bannerId){

        //if (picpath==null){
        bannerService.updateImgUrlService (picpath,session,bannerId);
        //}
    }
}
