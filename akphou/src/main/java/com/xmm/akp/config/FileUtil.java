package com.xmm.akp.config;

import com.xmm.akp.pojo.Picture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
@Component

@PropertySource(value= {"classpath:de.properties"})
public class FileUtil {

    @Value("${prop.picture-picture}")
    private String FOLDER;

    @Value("${prop.picture-url}")
    private String URL;


    public  String keeepPhoto(MultipartFile multipartFile,String photoAlias){ //保存上传的文件到磁盘

        File file=null;
        FileOutputStream fileOutputStream=null;

        file = new File(FOLDER , photoAlias);
        try {
            fileOutputStream=new FileOutputStream(file);
            try {
                fileOutputStream.write(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "null";
    }

   public Picture getPhotoInfo(String ptitle,String author,String psize,
                               String ptype,String psearch,String pyear,String plength,String photoAlias){ //获取图片信息（大小，高宽）

        File file=null;
        FileInputStream fileInputStream=null;
        BufferedImage sourceImg = null;
        Picture picture=new Picture();
        file = new File(FOLDER + photoAlias);
       System.out.println("获取文件为：" +FOLDER + photoAlias);
       try {
           fileInputStream = new FileInputStream(file);
           sourceImg = ImageIO.read(fileInputStream);
           double mm = Double.valueOf(String.format("%.1f", file.length() / 1024.0)); //图片大小（K）
           String size=sourceImg.getWidth()+ sourceImg.getHeight()+"";
           picture.setPtitle(ptitle);
           picture.setAuthor(author);
           picture.setPsize(size);
           picture.setPtype(ptype);
           picture.setPsearch(psearch);
           picture.setPyear(pyear);
           picture.setPlength(mm+"");
           picture.setPurl(URL+photoAlias);
           return picture;
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }finally {
           try {
               fileInputStream.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }

   public  boolean deleteAllFile(){
        File file = new File(FOLDER);
       System.out.println(file.getName());
       if(file!=null){
           File[] files = file.listFiles();
           if(files.length>0){
               for(File f :files){
                   f.delete();
               }
           }
       }
        return true;
   }

    public  boolean deleteFileByName(String fineName){
        File file = new File(FOLDER+fineName);
        if(file!=null){
            file.delete();
        }
        return true;
    }

}
