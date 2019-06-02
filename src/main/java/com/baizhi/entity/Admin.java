package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//管理员表对应的实体类

/**
 * @Component此注解表示 创建简单对象 默认构建的简单对象名称（ID值）为类名首字母小写
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Admin {
     private String id;
     private String username;
     private String password;

}
