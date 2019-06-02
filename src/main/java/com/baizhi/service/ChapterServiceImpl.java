package com.baizhi.service;

import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Chapter;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    @Override
    public Map<String, Object> selectChapterPage(String albumId, Integer pageNumber, Integer rows) {
        //准备返回客户端的数据 用map集合存储
        HashMap<String, Object> map = new HashMap<String, Object> ();
        // 1.将当前页号存入 map 集合
        map.put ("page",pageNumber);
        //2.将总条数存入map 集合中
        //调用Dao方法 获取总条数
        int count = chapterMapper.conuntChapter (albumId);
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
        Integer beginRows = (pageNumber-1)*rows;
        //查询结果
        List<Chapter> chapters = chapterMapper.selectByPrimaryAlbumKeyPage (albumId, beginRows, rows);
        //System.out.println ("ffffffff"+banners);

        //将查询的结果存储到 map 集合 中
        map.put ("rows",chapters);
        return map;
    }
    //删
    @Override
    public void deleteChapter(String[] id) {
        chapterMapper.deleteByIdsChapter (id);
    }
        //增
    @Override
    public String insertChapter(Chapter chapter) {
        //生成id
        chapter.setId (UUID.randomUUID ().toString ());
        //创建时间
        chapter.setCreatetime (new Date ());
        chapter.setTimes ("持续更新...");
        //包含上传音频 以及对音频处理
        chapterMapper.insertSelective (chapter);
        return chapter.getId ();
    }

    //改
    @Override
    public String updateChapter(Chapter chapter) {
        chapterMapper.updateByPrimaryKeySelective (chapter);
        return chapter.getId ();
    }
    //文件上传 音频 并处理时长 与大小 更新到数据库中
    @Override
    public Map<String, Object> upload(MultipartFile url, HttpSession session, String chapterId) {
        System.out.println ("上传音频--"+url);
        HashMap<String, Object> map = new HashMap<String, Object> ();
        //判断文件夹是否存在
        String realPath = session.getServletContext ().getRealPath ("/upload/audio");

        File file = new File (realPath);
        if (!file.exists ()){
            //如果文件不存在 创建
            file.mkdirs ();
        }
        //2.获取文件真名
        String originalFilename = url.getOriginalFilename ();
        //3.为防止同一个文件多次上传 被覆盖 加时间戳
        String str = new Date ().getTime () + "_" + originalFilename;
       //进行上传
        try {
            url.transferTo (new File (realPath,str));
            //获取音频时长
            AudioFile read = AudioFileIO.read (new File (realPath, str));
            AudioHeader audioHeader = read.getAudioHeader ();
            //获得时长
            int trackLength = audioHeader.getTrackLength ();
            //进行 时分秒换算处理
            String seconds = trackLength % 60 + "秒";
            String minute = trackLength / 60 + "分";
            //获取字节数
            long size = url.getSize ();
            //换算成MB
            String sizeMb = size / 1024 / 1024 + "MB";
            Chapter chapter = new Chapter ();
            chapter.setId (chapterId);
            chapter.setSize (sizeMb);
            chapter.setLength (minute+seconds);
            chapter.setUrl (str);
           chapterMapper.updateByPrimaryKeySelective (chapter);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return map;

    }
    //文件下载
    @Override
    public void downlodeAudoService(String audioName, HttpServletResponse response,HttpSession session) {
        System.out.println ("下载======"+audioName);
        //1.获取文件存储的路径  从session中获取
        String realPath = session.getServletContext ().getRealPath ("/upload/audio");

        //通过 拆分文件名  将上传到数据库时文件名添加的时间戳 分离 只剩文件名
        String s = audioName.split ("_")[1];

        //2.获取音频的文件夹
        File file = new File (realPath, audioName);
        //设置文件下载编码格式
        try {
            String encode = URLEncoder.encode (s, "UTF-8");
            response.setHeader ("content-disposition","attachment;filename="+encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace ();
        }

        //设置响应头 以附件形式下载

        ServletOutputStream outputStream = null;
        try {
         outputStream = response.getOutputStream ();
            FileUtils.copyFile (file,outputStream);
        } catch (IOException e) {
            e.printStackTrace ();
        }finally {
            try {
                outputStream.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
}
