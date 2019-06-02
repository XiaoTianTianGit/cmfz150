package com.baizhi;

import com.baizhi.dao.ChapterMapper;
import com.baizhi.entity.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 章节表对应测试
 * */
@SpringBootTest
@RunWith (SpringRunner.class)
public class ChapterTest {

    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    public void test1(){
        List<Chapter> chapters = chapterMapper.selectByPrimaryAlbumKeyPage ("1258223e-21c7-4b10-ad42-8ef976445767", 1, 1);

        //List<Chapter> chapters = chapterMapper.selectByPrimaryAlbumKey ("1258223e-21c7-4b10-ad42-8ef976445767",1,1);
        for (Chapter chapter : chapters) {
            System.out.println (chapter);
        }

    }

    @Test
    public void test2(){
        int i = chapterMapper.conuntChapter ("1258223e-21c7-4b10-ad42-8ef976445767");
        System.out.println (i);
    }



    @Test
    public void test3(){
        Chapter chapter = new Chapter ();
        chapter.setId ("991fcbc0-4f6e-4ae2-a6e4-6736365dbdd5");
        chapter.setSize ("fff");
        chapter.setLength ("ffff");
        chapter.setUrl ("fffff");
        int i = chapterMapper.updateByPrimaryKeySelective (chapter);
        System.out.println (i);
    }
}
