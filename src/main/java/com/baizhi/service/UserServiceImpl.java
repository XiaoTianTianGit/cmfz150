package com.baizhi.service;
import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> findAllUserPage(Integer PageNumber, Integer rows) {
        //准备返回客户端的数据 用map集合存储
        HashMap<String, Object> map = new HashMap<> ();
        // 1.将当前页号存入 map 集合
        map.put ("page",PageNumber);
        //2.将总条数存入map 集合中
        //调用Dao方法 获取总条数
        int count = userMapper.conuntUser ();
        map.put ("records",count);
        //3.将总页数 存入 map 中
        //初始化总页数
        Integer pageCount = 0;
        //计算总页数
        if (count%rows!=0){
            pageCount = count/rows+1;
        }else {
            pageCount = count/rows;
        }
        //System.out.println ("总页数==="+pageCount);
        //将计算获得的总页数 存储到 map 中
        map.put ("total",pageCount);
        //计算起始页号
        Integer beginRows = (PageNumber-1)*rows;
        //查询结果
        List<User> users = userMapper.selectUserPage (beginRows, rows);
        //将查询的结果存储到 map 集合 中
        map.put ("rows",users);
        return map;
    }
        //修改用户状态
    @Override
    public int updateByPrimaryKeySelective(User user) {
         userMapper.updateByPrimaryKeySelective (user);
        return 0;
    }
    //查询所有数据
    @Override
    public List<User> selectAllUserService() {
        List<User> users = userMapper.selectAllUser ();
        return users;
    }



    //查询每个地区用户注册的人数  测试 暂时用其他字段代替
    @Override
    public List<User> selectAllUser1Service() {
        return userMapper.selectAllUser1 ();
    }
}
