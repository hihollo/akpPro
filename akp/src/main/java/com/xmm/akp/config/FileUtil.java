package com.xmm.akp.config;

import com.xmm.akp.entity.Picture;
import com.xmm.akp.service.Picture.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

/**
 * 图片处理
 */
@Component
public class FileUtil {
    @Value("${prop.picture-url}")
    private String PICTURE_URL;

    @Value("${prop.upload-folder}")
    private String PICTURE_FOLDER;

    @Autowired
    private PictureService pictureService;

    private List<File> list = new ArrayList<>();

    public void addLocalPicture(String path) throws Exception { //将文件夹下的图片同时复制到另一个文件夹并将图片信息保存到数据库
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    addLocalPicture(fileIndex.getPath());
                } else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    list.add(fileIndex);
                }
            }
        }
        for (File f : list) { //每次复制一个图片到另一个文件夹，每次存一个图片信息到数据库
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(f));
            double mm = Double.valueOf(String.format("%.1f", f.length() / 1024.0)); //图片大小（K）
//                System.out.println("图片大小：" + mm + "K"); //大小
            String widthHight = sourceImg.getWidth() + "*" + sourceImg.getHeight();
//                System.out.println("图片宽高"+sourceImg.getWidth() * sourceImg.getHeight());  //像素
//                System.out.println("图片名称"+f.getName());
            String sameName = f.getName();
            String fileSuffix = sameName.substring(sameName.lastIndexOf("."));
            String alias = UUID.randomUUID().toString() + fileSuffix; //以防图片重名，造成url多个
            addPictureToDir(f, mm, widthHight, f.getName(), alias); //图片路径，大小，高宽，图片名称（在数据库中的）,图片名称（在url和磁盘中的）
            addPictureToDataBase(mm, widthHight, f.getName(), alias);
        }

    }

    public void addPictureToDir(File file, Double mm, String widthHight, String name, String alias) {
        // 打开输入流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 打开输出流
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(PICTURE_FOLDER + alias);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 读取和写入信息
        int len = 0;
        // 创建一个字节数组，当做缓冲区
        byte[] b = new byte[1024];
        while (true) {
            try {
                if (!((len = fis.read(b)) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.write(b, 0, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 关闭流  先开后关  后开先关
        try {
            fos.close(); // 后开先关
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close(); // 先开后关
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPictureToDataBase(Double mm, String widthHight, String name, String alias) {
        Picture picture = new Picture();
        int i = name.indexOf(".");
        picture.setPtitle(name.substring(0, i));
        picture.setPsize(widthHight);
        picture.setPtype("风景");
        picture.setPsearch("动漫风景");
        picture.setPurl(PICTURE_URL + alias);
        picture.setPyear("未知");
        picture.setPlength(mm.toString() + "K");
        pictureService.addAllPicture(picture);
    }

    public void delAllFile(File file) { //删除磁盘中的图片
        if (!file.exists()) {
            return;// 文件不存在，返回
        }
        File[] ff = file.listFiles();
        for (File def : ff) {
            if (def.isDirectory()) {
                delAllFile(def);
            }
            def.delete();
        }
    }

    public static byte[] imageByte(String path) { // 图片转为二进制
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output=  new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    public String getPictureSize(File f){ // 获取图片的宽高
        BufferedImage sourceImg = null;
        try {
            sourceImg = ImageIO.read(new FileInputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String widthHight = sourceImg.getWidth() + "*" + sourceImg.getHeight();
        return widthHight;
    }
    public String getPictureLength(File f){ // 获取图片大小如 4K
        BufferedImage sourceImg = null;
        try {
            sourceImg = ImageIO.read(new FileInputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        double mm = Double.valueOf(String.format("%.1f", f.length() / 1024.0)); //图片大小（K）
        System.out.println("图片大小：" + mm + "K"); //大小
        String m = " "+ mm;
        return m + 'K';
    }
    public File transferToFile(MultipartFile multipartFile) { //MultipartFile 转换为File
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();//"5900731f3004f9308252cd0aff5f6ac0.jpg"; //multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
//            file=File.createTempFile(filename[0] , "."+filename[1], new File("D:\\"));/home/pan/
            file=File.createTempFile(filename[0] , "."+filename[1], new File("/home/pan"));
            //file = new File(new File("D:\\").getAbsolutePath()+ "/" + originalFilename); 错误
           //multipartFile.transferTo(file); //错误
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("service中path" + file.getPath());
        return file;
    }

  public static void main(String [] args){
      String url = "http://10.9.9.131:8080/staticphoto/d2d7516d-55c3-4fe0-aac4-61edd0f6d7d5.jpg";
      int posi = url.lastIndexOf(":");
      String u = url.substring(posi);
      System.out.println(u);
    }


}
