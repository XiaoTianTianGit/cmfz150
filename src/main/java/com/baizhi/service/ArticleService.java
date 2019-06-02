package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {

    //分页展示所有
    public Map<String,Object> findAllArticlePage(Integer PageNumber, Integer rows);
    //添加一条数据 添加文章

    int addArticleService(Article record);

    //根据 id 删除一条信息
    int deleteByIdArticle(String id);
   //修改一条数据
   void updateByIdArticle(Article record);
}
