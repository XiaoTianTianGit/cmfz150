package com.baizhi;

import com.baizhi.dao.BannerMapper;
import com.baizhi.entity.Banner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith (SpringRunner.class)
public class PoiOutTest {

    // 将String 类型数据导出到 excel中
    @Test
    public  void test1() throws IOException {
        //1.创建Excel工作博对象
        HSSFWorkbook workbook = new HSSFWorkbook ();
        //2.创建工作表
        HSSFSheet sheet = workbook.createSheet ("test1");
        //3.根据工作表创建行  第一行
        HSSFRow row = sheet.createRow (0);
        //4.根据行创建单元格  第一行第一个单元格
        HSSFCell cell = row.createCell (0);
        //5.通过单元格设置值
        //将String 类型 写入
        cell.setCellValue ("hello");
        //6.将创建好的excel 写出到本地  给定写出的文件路径 及文件名称
        workbook.write (new File ("D:\\aa.xls"));

    }

    //将日期类型写出到 Excel
    @Test
    public void test2() throws IOException {
        //1.创建一个Excel 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook ();

        //对 日期格式进行 格式化处理  获得format
        HSSFDataFormat dataFormat = workbook.createDataFormat ();
        short format = dataFormat.getFormat ("yyyy年MM月dd日");
        // 通过工作博设置日期格式式
        HSSFCellStyle style = workbook.createCellStyle ();
        style.setDataFormat (format);

        //2.根据工作簿创建 工作表
        HSSFSheet sheet = workbook.createSheet ("test2");
        //设置列宽
        sheet.setColumnWidth (0,20*256);

        //3.根据工作表创建 行
        HSSFRow row = sheet.createRow (0);
        //4.根据行创建 单元格
        HSSFCell cell = row.createCell (0);
        //5.根据单元格设置值 日期格式 如果不处理 导出就是时间戳 所以需要处理
        Date date = new Date ();
        cell.setCellValue (date);
        //为日期设置格式 将设置好的格式给cell
        cell.setCellStyle (style);
        //6.将创建好的excel 写出到本地  给定写出的文件路径 及文件名称
        workbook.write (new File ("D:\\date.xls"));

    }


@Autowired
private BannerMapper bannerMapper;
    //将多行多列的数据导出到Excel表格中
    @Test
    public void test3() throws IOException {
        List<Banner> banners = bannerMapper.selectBannerPage (0, 3);
        System.out.println (banners);
        HSSFWorkbook workbook = new HSSFWorkbook ();
        //给表头设置样式
        HSSFFont font = workbook.createFont ();
        //字体设置为楷体
        font.setFontName ("楷体");
        //字体加粗
        font.setBold (true);
        //颜色 设置为 红色
        font.setColor (Font.COLOR_RED);

        //对日期进行处理
        HSSFDataFormat dataFormat = workbook.createDataFormat ();
        short format = dataFormat.getFormat ("yyyy年MM月dd日");

        //通过 workboot 创建 style
        HSSFCellStyle style = workbook.createCellStyle ();
        style.setFont (font);
        //将设置好的日期格式给  style 属性
        style.setDataFormat (format);
        //设置居中
        style.setAlignment (HorizontalAlignment.CENTER);
        //3.根据工作表创建 行
        HSSFSheet sheet = workbook.createSheet ("test3");
         sheet.setColumnWidth (5,20*256);
        //如果数据是多行多列
        //1.先设置第一行 表头
        HSSFRow row = sheet.createRow (0);

        // 设置表头  表头包含多个单元格  因此是一个数组
        String [] tetle = {"id","图片名称","图片路径","图片描述","图片状态","创建日期"};
        // 对每个单元格进行逐一处理
        for (int i = 0; i < tetle.length; i++) {
            String s = tetle[i];
            //根据行 创建每一个 单元格
            HSSFCell cell = row.createCell (i);
            //在给每一个单元格设置值
            cell.setCellValue (s);
            //将设置好的样式 交给 cell
            cell.setCellStyle (style);
        }

        //对数据库查询到的list 进行处理  并导出到Excel表格中
        for (int i = 0; i < banners.size ();i++) {
           //  设置每一行的数据 第一行为标题行 从第二行开始
            HSSFRow row1 = sheet.createRow (i + 1);
            //设置每一行的每一个单元格
            //设置第一行 第一个单元格
       row1.createCell (0).setCellValue (banners.get(i).getId ());
       row1.createCell (1).setCellValue (banners.get (i).getPicname ());
       row1.createCell (2).setCellValue (banners.get (i).getPicpath ());
       row1.createCell (3).setCellValue (banners.get (i).getDescription ());
       row1.createCell (4).setCellValue (banners.get (i).getStatus ());
       //对日期格式进行处理
            HSSFCell cell = row1.createCell (5);
            cell.setCellValue (banners.get (i).getCreatetime ());
            cell.setCellStyle (style);
        }

        //6.将创建好的excel 写出到本地  给定写出的文件路径 及文件名称
        workbook.write (new File ("D:\\excel6.xls"));
    }
}
