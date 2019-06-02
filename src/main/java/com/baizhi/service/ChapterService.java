package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {
    //分页展示专辑 下对应的章节
    Map<String,Object> selectChapterPage(String albumId,Integer pageNumber, Integer rows);
    //删
    void deleteChapter(String[] id);
    //增
    String insertChapter(Chapter chapter);
    //改
    String updateChapter(Chapter chapter);
    //处理文件上传
    //文件上传   上传音频
    public Map<String,Object> upload(MultipartFile url, HttpSession session, String chapterId);
    //文件下载
    public void downlodeAudoService(String audioName, HttpServletResponse response,HttpSession session);

}
