package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);

    // 根据 父级 id 查询 所有的专辑 分页展示
    public List<Chapter> selectByPrimaryAlbumKeyPage(@Param("albumId") String albumId, @Param("benginRows") Integer benginRows, @Param("rows") Integer rows);

    //根据专辑id 查询总共有多少条数据
    int conuntChapter(@Param("albumId") String albumId);

    //根据 id批量删除
    public void deleteByIdsChapter(String[] id);

}