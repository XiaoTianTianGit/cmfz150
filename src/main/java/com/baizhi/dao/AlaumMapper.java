package com.baizhi.dao;

import com.baizhi.entity.Alaum;
import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlaumMapper {
    int deleteByPrimaryKey(String[] id);

    int insert(Alaum record);

    int insertSelective(Alaum record);

    Alaum selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Alaum record);

    int updateByPrimaryKey(Alaum record);
    //查询总共有多少条数据
    int conuntAlaum();

    //分页展示专辑
    List<Alaum> selectAlaumPage(@Param("benginRows") Integer benginRows, @Param ("rows") Integer rows);
    //根据id 修改图片的路径

    public void updateImgUrl(Alaum alaum);
}