package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

//管理员对应的Dao接口
public interface AdminDao {
    //登录
    public Admin selectByName(@Param("username") String username);
}
