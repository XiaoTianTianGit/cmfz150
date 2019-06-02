package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Alaum implements Serializable {
    private String id;
    private String title;
    private String score;
    private String brodecast;
    private String author;
    private String brief;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publictime;
    private String count;
    private String coverimg;
    private Integer status;
    /*serialVersionUID作用：是序列化时保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。
    相当于java类的身份证。主要用于版本控制。
         有两种生成方式: 1.默认:  private static final long serialVersionUID = 1L;
                          2. 根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段
                        eg: private static final long serialVersionUID = xxxxL;
                        */
    private static final long serialVersionUID = 1L;
}