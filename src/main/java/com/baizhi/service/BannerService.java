package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface BannerService {
    // 添加一条数据
    public String addBanner(Banner banner);
    //根据 id 修改一条数据
    public String updateBanner(Banner banner);
    // 根据 id 批量数据
    public void deteleBanner(String[] ids);
    //根据 id 查询
    public Banner findByIdBanner(String id);
    //分页展示所有
    public Map<String,Object> findAllBannerPage(Integer PageNumber, Integer rows);
    //查询总条数
    public int countBannerService();

    //文件上传 并 根据id 修改图片路径
    public void updateImgUrlService(MultipartFile picpath, HttpSession session, String bannerId);

    public void addAllBannerService(List<Banner> list);

}
