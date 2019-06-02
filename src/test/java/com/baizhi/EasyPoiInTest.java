package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baizhi.entity.User;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class EasyPoiInTest {


    @Test
    public void test1() throws Exception {

        ImportParams importParams = new ImportParams ();
        importParams.setTitleRows (1);
        importParams.setHeadRows (1);
//new File("d:/userAllEasyPOI02.xls"),

        List<User> result = ExcelImportUtil.importExcel( new FileInputStream ("D:/userAllEasyPOI02.xls"),User.class,importParams);
        for (User user : result) {
            System.out.println (user);
        }

    }
}
