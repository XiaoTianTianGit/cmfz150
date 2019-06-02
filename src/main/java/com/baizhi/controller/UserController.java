package com.baizhi.controller;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDtoTest;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    //分页展示所属专辑下的所有章节
    @RequestMapping("/showUserPager.do")
    public Map<String,Object> showUserPager(Integer page, Integer rows){ ;
        Map<String, Object> allUserPage = userService.findAllUserPage (page, rows);
        return allUserPage ;
    }

    //增删改  此处只修改用户状态
    //增删改
    @RequestMapping("/edit.do")
    @ResponseBody
    public Map<String,Object> edit(String oper, User user){
        Map<String, Object> map = new HashMap<> ();
        //判断具体是什么操作
        if("add".equals (oper)){
        }else if ("edit".equals (oper)){
            userService.updateByPrimaryKeySelective (user);
        }else if ("del".equals (oper)){

        }
        return map;
    }

    //导出数据库中的数据  导出全部   无需传参数  如果导出指定的数据 需要传参数
 /*   @RequestMapping("/poiOutUser")
    public void poiOutUser() throws IOException {

        // 1.数据库查询 准备数据
        List<User> list = userService.selectAllUserService ();
        //2.创建工作博
        HSSFWorkbook workbook = new HSSFWorkbook ();
        //3.通过工作簿创建工作表
        HSSFSheet sheet = workbook.createSheet ("用户表");
        //4.通过工作表创建行
        //第0行表示标题  表头
        HSSFRow row = sheet.createRow (0);
        //设置表头
        String[] titles = {"编号","头像","法名","名称"};
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            //通过行设置单元格 在通过单元格设置值
            row.createCell (i).setCellValue (title);
        }
        //对查询的数据进行处理
        for (int i = 0; i < list.size (); i++) {
            //通过工作表 设置 行
            HSSFRow row1 = sheet.createRow (i + 1);
            //通过行设置单元格 在通过单元格设置值
            row1.createCell (0).setCellValue (list.get (i).getId ());
            row1.createCell (1).setCellValue (list.get (i).getPhoto ());
            row1.createCell (2).setCellValue (list.get (i).getDharmaname ());
            row1.createCell (3).setCellValue (list.get (i).getName ());
        }
        //将处理的数据 写出
        workbook.write (new File ("d:/userAll.xls"));
    }*/

    //导入数据

    @RequestMapping("/poiInUser")
   public void poiInUser(MultipartFile file) throws IOException {
        System.out.println ("---------");
        InputStream inputStream = file.getInputStream ();
        HSSFWorkbook workbook = new HSSFWorkbook ();
        HSSFSheet sheet = workbook.getSheet ("用户表");
        int lastRowNum = sheet.getLastRowNum ();
        System.out.println ("lastRowNum = "+lastRowNum);
        for (int i = 0; i <= lastRowNum; i++) {
            String id = sheet.getRow (i).getCell (0).getStringCellValue ();
            String photo = sheet.getRow (i).getCell (1).getStringCellValue ();
            String Dharmaname = sheet.getRow (i).getCell (2).getStringCellValue ();
            String name = sheet.getRow (i).getCell (2).getStringCellValue ();
            System.out.println ("数据==="+id+photo+Dharmaname+name);

        }
    }



  //使用EasyPOI 进行导出数据
 @RequestMapping("/EasyPoiOutUser")

public void EasyPoiOutUser(HttpSession session) throws IOException {

     String realPath = session.getServletContext ().getRealPath ("/upload/img/");

     // 1.数据库查询 准备数据
     List<User> list = userService.selectAllUserService ();
     // 拼接图片路径
     for (User user : list) {
         user.setPhoto (realPath+user.getPhoto ());
     }
     for (User user : list) {
         System.out.println (user);
     }
     //创建工作博
     //HSSFWorkbook workbook = new HSSFWorkbook ();
  Workbook workbook = ExcelExportUtil.exportExcel (new ExportParams ("用户表信息", "用户"), User.class, list);

     workbook.write (new FileOutputStream ("D:/userAllEasyPOI02.xls"));


 }


 //测试 Echarts 1

    @RequestMapping("/getTest")
    public @ResponseBody List<User> getTest(){
        // 1.数据库查询 准备数据
        List<User> list = userService.selectAllUserService ();
        return list;
    }

    // 测试Echarts 2  用户分布图
        // 查询每个地区注册的人数



    @RequestMapping("/getTest1")
    public @ResponseBody  List<UserDtoTest> getTest1(){
        // 1.数据库查询 准备数据
        List<User> list = userService.selectAllUser1Service ();
   List<UserDtoTest> listnew = new ArrayList<> ();
        for (User user : list) {
            UserDtoTest userDtoTest = new UserDtoTest ();
            userDtoTest.setName (user.getPrivoince ());
            userDtoTest.setValue (user.getSalt ());
            listnew.add (userDtoTest);
           // System.out.println (user);
        }
        return listnew;
    }



    @RequestMapping("/getTest2")
    public @ResponseBody  List<UserDtoTest> getTest2(){
        // 1.数据库查询 准备数据
        List<User> list = userService.selectAllUser1Service ();
        List<UserDtoTest> listnew = new ArrayList<> ();
        for (User user : list) {
            UserDtoTest userDtoTest = new UserDtoTest ();
            userDtoTest.setName (user.getPrivoince ());
            userDtoTest.setValue (user.getSalt ());
            listnew.add (userDtoTest);
            // System.out.println (user);
        }
        return listnew;
    }




    @RequestMapping("/getTest3")
    public @ResponseBody  List<UserDtoTest> getTest3(){
        // 1.数据库查询 准备数据
        List<User> list = userService.selectAllUser1Service ();
        List<UserDtoTest> listnew = new ArrayList<> ();
        for (User user : list) {
            UserDtoTest userDtoTest = new UserDtoTest ();
            userDtoTest.setName (user.getPrivoince ());
            userDtoTest.setValue (user.getSalt ());
            listnew.add (userDtoTest);
            // System.out.println (user);
        }
        return listnew;
    }

}
