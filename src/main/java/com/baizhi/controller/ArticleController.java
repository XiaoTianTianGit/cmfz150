package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 文章模块控制类
 * */
@RestController
@RequestMapping("/Article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //分页展示所属专辑下的所有章节
    @RequestMapping("/showArticlePager.do")
    public Map<String, Object> showArticlePager(Integer page, Integer rows) {
        Map<String, Object> allArticlePage = articleService.findAllArticlePage (page, rows);
        return allArticlePage;
    }

    //删除文章
    @RequestMapping("/edit.do")
    public void edit(String id) {
        articleService.deleteByIdArticle (id);
    }
    //添加文章
    @RequestMapping("/addArticle.do")
    public void deleteArticle(Article article){
        int i = articleService.addArticleService (article);
    }

    //更新文章
    @RequestMapping("/updateArticle.do")
     public void updateArticle(Article article){
       articleService.updateByIdArticle (article);
    }
    //1. 处理请求
    //配合 kindeditor 上传文章插图  接收数据

    @RequestMapping("/uploadImg.do")
    public HashMap<String, Object> uploadImg(HttpServletRequest request, MultipartFile img) {
        //获取上传文件路径
        String realPath = request.getSession ().getServletContext ().getRealPath ("/upload/img");
        File file = new File (realPath);
        //判断文件是否存在
        if (!file.exists ()) {
            file.mkdirs ();
        }
        String originalFilename = img.getOriginalFilename ();
        String s = new Date ().getTime () + "_" + originalFilename;
        try {
            img.transferTo (new File (realPath, s));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        HashMap<String, Object> map = new HashMap<> ();
        map.put ("error", 0);

        // 获取访问路径  相当于 localhost
        InetAddress localHost = null;
        try {
            //获取协议  相当于 http
            String scheme = request.getScheme ();
            localHost = InetAddress.getLocalHost ();// localhost
            //获取的 需要进行处理
            String string = localHost.toString ();
            String localhost = string.split ("/")[1];
            //获取端口号 项目访问的端口号
            int serverPort = request.getServerPort (); // 9999
            //动态获取项目名
            String contextPath = request.getContextPath (); //cmfz
            String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/upload/img/" + s;
            map.put ("url", url);

        } catch (UnknownHostException e) {
            e.printStackTrace ();
        }
        return map;
    }

    // 2.处理响应
    //   配合 kindeditor 上传文章插图  作用： 指定浏览远程图片的服务器端程序
    @RequestMapping("/getAll.do")
    public  Map<String,Object> getAll(HttpServletRequest request) throws UnknownHostException {
        HashMap<String, Object> map = new HashMap<> ();
        //获取上传文件路径
        String realPath = request.getSession ().getServletContext ().getRealPath ("/upload/img");
        File file = new File (realPath);
        ArrayList<Object> list = new ArrayList<> ();
        //获取所有图片的对象
        String[] imgs = file.list ();
        for (String img : imgs) {
            //此 map 集合中 封装的是图片具体的属性  大小 名称  .....
            HashMap<Object, Object> map1 = new HashMap<> ();
            map1.put ("is_dir",false);
            map1.put ("has_file", false);
            //获取图片的大小
            File file1 = new File (realPath,img);
            long length = file1.length ();//图片的大小
            map1.put("filesize",length);
              map1.put ("dir_path","");
              map1.put ("is_photo",true);
              //存储文件的格式   后缀  需要动态获取
            String extension = FilenameUtils.getExtension (img);
            map1.put ("filetype",extension); //图片的格式
            map1.put ("filename",img);
            //拆分图片的时间戳  获取图片的
            String s = img.split ("_")[0];
            //将时间戳转换为日期类型
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            Long aLong = new Long (s);
            Date date = new Date (aLong);
            String format = simpleDateFormat.format (date);
            map1.put ("datetime",format);//图片日期
            //将 map 放入到 list中
            list.add (map1);
        }
        map.put ("file_list",list);
        map.put ("moveup_dir_path","url");

        //图片访问路径

        //获取图片上传访问路径
        String scheme = request.getScheme ();
        InetAddress localHost = InetAddress.getLocalHost ();// localhost
        //获取的 需要进行处理
        String string = localHost.toString ();
        String localhost = string.split ("/")[1];
        //获取端口号 项目访问的端口号
        int serverPort = request.getServerPort (); // 9999
        //动态获取项目名
        String contextPath = request.getContextPath (); //cmfz
        String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/upload/img/";
        map.put ("current_url", url);//图片的访问路径
        //获取图片的总条数
        int length = imgs.length;
        map.put ("total_count",length );//图片的总数量
        return map;
    }
}
