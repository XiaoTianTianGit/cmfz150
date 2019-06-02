package com.baizhi;
//专辑测试

import com.baizhi.dao.AlaumMapper;
import com.baizhi.entity.Alaum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
@RunWith (SpringRunner.class)
public class AlaumTest {
    @Autowired
    private AlaumMapper alaumMapper;

    @Test
    public void test1(){
        Alaum alaum = alaumMapper.selectByPrimaryKey ("1");
        System.out.println (alaum);
    }


    @Test
    public void test2(){
        Alaum alaum = new Alaum ();
        //对 id 进行处理
        alaum.setId (UUID.randomUUID ().toString ());
        // 对分数进行处理
        alaum.setScore ("10");
        //对创建日期进行处理
        alaum.setPublictime (new Date ());
        alaum.setAuthor ("ss");
        alaum.setBrief ("fff");
        alaum.setBrodecast ("dddd");
        alaum.setStatus (1);
        alaum.setTitle ("DDDD");
        alaum.setCount ("2222");
        alaum.setCoverimg ("ddd");
        alaumMapper.insertSelective (alaum);
    }


    @Test
    public void test3(){
        Alaum alaum = new Alaum ();
        //对 id 进行处理
        alaum.setId ("1");
        // 对分数进行处理
        alaum.setScore ("10");
        //对创建日期进行处理
        alaum.setPublictime (new Date ());
        alaum.setAuthor ("ss");
        alaum.setBrief ("fff");
        alaum.setBrodecast ("dddd");
        alaum.setStatus (1);
        alaum.setTitle ("DDDD");
        alaum.setCount ("2222");
        alaum.setCoverimg ("ddd");
        int i = alaumMapper.updateByPrimaryKey (alaum);
    }

    @Test
    public void test4(){
        Alaum alaum = new Alaum ();
        //对 id 进行处理
        alaum.setId ("e5c12b72-cf0c-459a-b4c4-c88408350c83");
        alaum.setCoverimg ("dddhhh");
        alaumMapper.updateImgUrl (alaum);

    }


}
