package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     *    @ExcelIgnore 表示 忽略 id excel导出不包含此属性
     * */
    @Excel (name="编号")
    private String id;
    @Excel (name="头像",type = 2,width = 15,height = 30)
    private String photo;
    @Excel (name="法名")
    private String dharmaname;
   @Excel (name="法名")
    private String name;
    @Excel (name="性别")
    private String sex;
   @Excel (name="省份")
    private String privoince;
    @Excel (name="城市")
    private String city;
    @Excel (name="签名")
    private String sign;
    @Excel (name="手机号")
    private String phonenum;
    @Excel (name="密码")
    private String password;
    @Excel (name="盐值")
    private String salt;
    @Excel (name="日期",format = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creattime;
     @Excel (name="状态")
    private String status;
}