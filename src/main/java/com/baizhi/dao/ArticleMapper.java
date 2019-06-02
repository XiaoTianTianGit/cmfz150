package com.baizhi.dao;

import com.baizhi.entity.Article;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章对应的dao接口
 * */
public interface ArticleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //查询总共有多少条数据
    int conuntArticle();
    //分页 展示所有数据
    List<Article> selectArticlePage(@Param("benginRows") Integer benginRows, @Param ("rows")Integer rows);
}