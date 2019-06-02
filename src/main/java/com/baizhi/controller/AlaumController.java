package com.baizhi.controller;

import com.baizhi.entity.Alaum;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlaumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 专辑表的控制类
 * */

@RestController
@RequestMapping("/Alaum")
public class AlaumController {
    @Autowired
    private AlaumService alaumService;
    //分页展示所有专辑信息
    @RequestMapping("/showPageAlaum.do")
    public @ResponseBody Map<String,Object> showPageAlaum(Integer page, Integer rows){
        Map<String, Object> mapAlaumPage = alaumService.selectAlaumPage (page, rows);
        return  mapAlaumPage;
    }


    // 实现 增删改
    @RequestMapping("/edit.do")
    public Map<String, Object>  edit(java.lang.String oper, Alaum alaum, java.lang.String[] id){
        HashMap<String, Object> map = new HashMap<String, Object> ();
        //判断具体是哪个操作  分别调用对应的 业务层
        //判断是哪个操作 分别调用对应的增删改方法
        if ("add".equals (oper)){
            System.out.println ("接收到的参数======"+alaum);
            String s = alaumService.addAlaum (alaum);
            map.put ("message","添加成功");
            map.put ("AlaumId",s);
        }else if("edit".equals (oper)){


/*
        if ("".equals (alaum.getCoverimg ())){
            //说明 用户不想修改图片 因此 需要在页面判断是否执行文件上传的方法 ，
                // 如果返回“” 说明无需调用 上传方法
                map.put ("AlaumId","");
            }*/

            String s = alaumService.updateAlaum (alaum);
            map.put ("message","修改成功");
            map.put ("AlaumId",s);

        }else if ("del".equals (oper)){

            alaumService.deteleAlaum (id);
            map.put ("message","删除成功");

        }
        return map;
    }
    //图片上传

    @RequestMapping("/uplode.do")
    public void uplode(MultipartFile coverimg, HttpSession session, String alaumId){

        if (!"".equals (coverimg)){
            alaumService.updateImgUrl (coverimg,session,alaumId);
        }

    }
}
