package com.baizhi.controller;
//验证码的Controller
import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/Code")
public class CodeController {
    //生成验证码
@RequestMapping("/getCode.do")
    public void getCode(HttpSession session, HttpServletResponse response){
        //绘制图片中的数字
    //1.生成随机字符串  4位
    String securityCode = ValidateImageCodeUtils.getSecurityCode ();
    session.setAttribute ("securityCode",securityCode);
   //2.绘制验证码图片
    BufferedImage image = ValidateImageCodeUtils.createImage (securityCode);
    ServletOutputStream outputStream = null;
    try {
        outputStream = response.getOutputStream ();
        //3.用流写出图片
        ImageIO.write (image,"png",outputStream);
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
