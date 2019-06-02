package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //分页展示所有
    public Map<String,Object> findAllUserPage(Integer PageNumber, Integer rows);

        //修改用户状态
        int updateByPrimaryKeySelective(User user);
    //查询所有   导出 数据库中所有数据
    public List<User> selectAllUserService();

//查询每个地区用户注册的人数  测试 暂时用其他字段代替

    public List<User> selectAllUser1Service();

}
