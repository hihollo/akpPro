/*
package com.xmm.akp.config;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGDecodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

import javax.imageio.ImageIO;

@SpringBootTest(classes = {TestReadFile.class})
public class TestReadFile {
    //@Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER; // 文件存储位置
    @Value("${prop.picture-url}")
    private String PICTURE_URL;
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ReadFile readFile;

    @Test
    public void testAddLocalPicture(){ //将本地某个文件夹的所有图片存到服务器和图片信息存到数据库
        try {
            fileUtil.addLocalPicture("C:\\Users\\MING\\Pictures\\wallPaper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
/*@Test
    public void testAddToDataBase(){
        fileUtil.addPictureToDataBase();
    }
*//*

    @Test
    public void testCreatFile(){
        readFile.creatFile();
    }

    @Test
    public void test1(){
        String str = "0123.455";
        int i=str.indexOf(".");
        System.out.println(str.indexOf("."));
        System.out.println(str.substring(0,i));

    }


    @Test
    public void testSepa(){ //测试系统分隔符
       String sepa = java.io.File.separator;
       String path="C%3A%5C%5CUsers%5C%5CMING%5C%5CPictures%5C%5Cwal=";
        System.out.println(sepa);
        path = path.replace("=","");
        path = path.replace("%5C%5C",",");
        path = path.replace("%3A",":");
        path = path.replace(",","\\\\");
        System.out.println(path);



    }


}
*/
