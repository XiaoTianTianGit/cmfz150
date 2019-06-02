package com.baizhi;

import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PoiInTest {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BannerMapper bannerMapper;
    //将 excel 文件导入到程序中
    @Test
    public void test1() throws IOException {
        //1.创建一个工作博  并且指定要读取的文件
        HSSFWorkbook workbook = new HSSFWorkbook (new FileInputStream ("D:/excel6.xls"));
        //2.根据工作博获得工作表
        HSSFSheet sheet = workbook.getSheet ("test3");
        //3. 根据工作表 sheet 获得行
        //获得表格中最后一行的一个下标
        int lastRowNum = sheet.getLastRowNum ();
        System.out.println ("获得表格中最后一行的一个下标===" + lastRowNum);
        //创建 list 集合 存放 导入的数据
        List<Banner> list = new ArrayList<Banner> ();
        for (int i = 1; i <= lastRowNum; i++) {
            //链式调用  通过 工作表获得 行，通过行 获得单元格 通过单元格获得值
            String id = sheet.getRow (i).getCell (0).getStringCellValue ();
            String picname = sheet.getRow (i).getCell (1).getStringCellValue ();
            String picpath = sheet.getRow (i).getCell (2).getStringCellValue ();
            String description = sheet.getRow (i).getCell (3).getStringCellValue ();
            String status = sheet.getRow (i).getCell (4).getStringCellValue ();
            Date createtime = sheet.getRow (i).getCell (5).getDateCellValue ();
            //System.out.println ("数据" + id + picname + picpath + description + status + createtime);
            //将 此属性 一一存放到 实体中
            Banner banner = new Banner (id, picname, picpath, description, status, createtime);
            list.add (banner);
        }
        //将 读入程序中处理过的 数据 批量加入到数据库中
         bannerService.addAllBannerService (list);
    }


    @Test
    public void test2() {
        ArrayList<Banner> banners = new ArrayList<> ();
        Banner banner1 = new Banner ("1", "vv", "dddd", "vvvvvv", "vvvv", new Date ());
        Banner banner2 = new Banner ("2", "vv1", "dddd1", "vvvvvv1", "vvvv1", new Date ());
        banners.add (banner1);
        banners.add (banner2);
        bannerMapper.addAllBanner (banners);
    }
}
