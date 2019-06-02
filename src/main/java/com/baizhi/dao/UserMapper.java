package com.baizhi.dao;

import com.baizhi.entity.Banner;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户对应的dao接口
 * */
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    //查询总共有多少条数据
    int conuntUser();
    //分页 展示所有数据
    List<User> selectUserPage(@Param("benginRows") Integer benginRows,@Param ("rows")Integer rows);

    //查询所有   导出 数据库中所有数据
    public List<User> selectAllUser();

//查询每个地区用户注册的人数  测试 暂时用其他字段代替

    public List<User> selectAllUser1();
}