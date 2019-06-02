package com.baizhi.service;

import com.baizhi.entity.Alaum;
import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 专辑模块对应的service
 * */
public interface AlaumService {

    //分页展示专辑
    Map<String,Object> selectAlaumPage(Integer pageNumber, Integer rows);

    //添加一条数据
    public String addAlaum(Alaum record);
    //根据 id 修改一条数据
    public String updateAlaum(Alaum alaum);
    // 根据 id 删除一条数据
    public void deteleAlaum(String[] ids);
    //根据 id 查询
    public Alaum findByIdAlaum(String id);

    //插图上传
    //文件上传 并 根据id 修改图片路径
    public void updateImgUrl(MultipartFile coverimg, HttpSession session, String alaumId);

}
