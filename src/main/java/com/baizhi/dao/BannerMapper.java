package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    //根据 id删除
    int deleteByPrimaryKey(String[] id);
    //添加一条数据
    int insert(Banner record);
    //添加一条数据
    int insertSelective(Banner record);
    //根据 id 查询
    Banner selectByPrimaryKey(String id);
    //修改一条数据
    int updateByPrimaryKeySelective(Banner record);
    //根据 id 修改
    int updateByPrimaryKey(Banner record);
    //查询总共有多少条数据
    int conuntBanner();
    //分页 展示所有数据
    List<Banner> selectBannerPage(@Param ("benginRows") Integer benginRows, @Param ("rows") Integer rows);
    //根据id 修改图片的路径
    public void updateImgUrl(Banner banner);

    // 批量插入
    public void addAllBanner(List<Banner> list);
}