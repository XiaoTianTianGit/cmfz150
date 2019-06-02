package com.baizhi.controller;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 章节表对应控制层
 * */

@RestController
@RequestMapping("/Chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //分页展示所属专辑下的所有章节
    @RequestMapping("/showChapterPager.do")
    public Map<String,Object> showChapterPager(String alaumId, Integer page, Integer rows){ ;
        Map<String, Object> chapterPageMap = chapterService.selectChapterPage (alaumId, page, rows);
        return chapterPageMap;
    }


    //增删改
    @RequestMapping("/edit.do")
    @ResponseBody
    public Map<String,Object> edit(String oper, Chapter chapter, String[] id){
        Map<String, Object> map = new HashMap<> ();
        //判断具体是什么操作
        if("add".equals (oper)){
            String s = chapterService.insertChapter (chapter);
            map.put ("message","添加成功");
            map.put ("chapterId",s);
        }else if ("edit".equals (oper)){
            String s = chapterService.updateChapter (chapter);
            map.put ("message","修改成功");
            map.put ("chapterId",s);
        }else if ("del".equals (oper)){
            chapterService.deleteChapter (id);
        }
        return map;
    }


    //文件上传   上传音频
    @RequestMapping("/upload.do")
    public Map<String,Object> upload(MultipartFile url, HttpSession session,String chapterId){
        Map<String, Object> map = new HashMap<String, Object> ();
            chapterService.upload (url,session,chapterId);
        return map;

    }

    //文件下载  下载音频
    @RequestMapping("/downlodeAudo.do")
    public void downlodeAudo(String audioName, HttpServletResponse response,HttpSession session){
        chapterService.downlodeAudoService (audioName,response,session);
    }


}
