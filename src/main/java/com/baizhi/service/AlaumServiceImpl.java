package com.baizhi.service;

import com.baizhi.dao.AlaumMapper;
import com.baizhi.entity.Alaum;
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
public class AlaumServiceImpl implements AlaumService {

    @Autowired
    private AlaumMapper alaumMapper;

    @Override
    public Map<String, Object> selectAlaumPage(Integer pageNumber, Integer rows) {
        //准备返回客户端的数据 用map集合存储
        HashMap<String, Object> map = new HashMap<> ();
        // 1.将当前页号存入 map 集合
        map.put ("page",pageNumber);
        //2.将总条数存入map 集合中
        //调用Dao方法 获取总条数
        int count = alaumMapper.conuntAlaum ();
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
        Integer beginRows = (pageNumber-1)*rows;
        //查询结果
        List<Alaum> alaums = alaumMapper.selectAlaumPage (beginRows, rows);
        //System.out.println ("ffffffff"+banners);

        //将查询的结果存储到 map 集合 中
        map.put ("rows",alaums);
        return map;
    }
    //添加一条数据
    @Override
    public String addAlaum(Alaum alaum) {
        //对 id 进行处理
        alaum.setId (UUID.randomUUID ().toString ());
        // 对分数进行处理
        alaum.setScore ("10");
        //对创建日期进行处理
         alaum.setPublictime (new Date ());
    alaumMapper.insertSelective (alaum);
        return alaum.getId ();
    }
    //根据id修改一条数据
    @Override
    public String updateAlaum(Alaum alaum) {
        // 对分数进行处理
       // alaum.setScore ("10");

        //对创建日期进行处理
        alaum.setPublictime (new Date ());
        alaumMapper.updateByPrimaryKey (alaum);
        return alaum.getId ();
    }

     //根据id删除
    @Override
    public void deteleAlaum(String[] ids) {
        alaumMapper.deleteByPrimaryKey (ids);
    }
    //根据id查询
    @Override
    public Alaum findByIdAlaum(String id) {
        return null;
    }
    // 图片上传
    @Override
    public void updateImgUrl(MultipartFile coverimg, HttpSession session, String alaumId) {
        System.out.println ("alaumId=="+alaumId);
        System.out.println ("coverimg"+coverimg);
        System.out.println ("coverimg"+coverimg.getOriginalFilename ());
        //获取图片路径
        String realPath = session.getServletContext ().getRealPath ("/upload/img");
        File file = new File (realPath);
        //判断上传的文件夹是否存在
        if (!file.exists ()){
            file.mkdirs ();
        }

        String originalFilename = coverimg.getOriginalFilename ();
        String str = new Date ().getTime () + "_" + originalFilename;
        try {
            coverimg.transferTo (new File (realPath,str));
            Alaum alaum = new Alaum ();
            alaum.setId (alaumId);
            alaum.setCoverimg (str);
         alaumMapper.updateImgUrl (alaum);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
