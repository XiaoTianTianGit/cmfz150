package com.baizhi.service;
import com.baizhi.dao.ArticleMapper;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Map<String, Object> findAllArticlePage(Integer PageNumber, Integer rows) {
        //准备返回客户端的数据 用map集合存储
        HashMap<String, Object> map = new HashMap<String, Object> ();
        // 1.将当前页号存入 map 集合
        map.put ("page",PageNumber);
        //2.将总条数存入map 集合中
        //调用Dao方法 获取总条数
        int count = articleMapper.conuntArticle ();
        map.put ("records",count);
        //3.将总页数 存入 map 中
        //运用三目运算符
       int pageCount = count % rows == 0 ? count / rows : count / rows + 1;
        //将计算获得的总页数 存储到 map 中
        map.put ("total",pageCount);
        //计算起始页号
        Integer beginRows = (PageNumber-1)*rows;
        //查询结果
        List<Article> articles = articleMapper.selectArticlePage (beginRows, rows);
        //将查询的结果存储到 map 集合 中
        map.put ("rows",articles);
        return map;
    }

        //添加文章
    @Override
    public int addArticleService(Article article) {
        article.setId (UUID.randomUUID ().toString ());
        article.setPublictime (new Date ());
        article.setGuruid ("001");
        int i = articleMapper.insertSelective (article);
        return i;
    }
        //删除一条数据
    @Override
    public int deleteByIdArticle(String id) {
        int i = articleMapper.deleteByPrimaryKey (id);
        return i;
    }
        //修改一条信息
    @Override
    public void updateByIdArticle(Article article) {
        System.out.println ("record======"+article);
        articleMapper.updateByPrimaryKeySelective (article);


    }
}
