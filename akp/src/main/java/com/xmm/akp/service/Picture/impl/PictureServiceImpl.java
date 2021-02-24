package com.xmm.akp.service.Picture.impl;

import com.github.pagehelper.PageHelper;
import com.xmm.akp.config.FileUtil;
import com.xmm.akp.config.PageBean;
import com.xmm.akp.dao.PictureMapper;
import com.xmm.akp.entity.Picture;
import com.xmm.akp.service.Picture.PictureService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {
    private static Logger log = Logger.getLogger(PictureServiceImpl.class);
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private FileUtil fileUtil;
    @Override
    public boolean addPicture(Picture picture, MultipartFile file) { //添加图片
//        String fileSuffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        String alias = UUID.randomUUID().toString()+fileSuffix; //以防图片重名，造成url多个
        String alias =picture.getPurl().substring(picture.getPurl().lastIndexOf("/"));
        System.out.println(picture.toString());
        if (Objects.isNull(file) || file.isEmpty()) {
            log.warn("文件为空,存储失败！！");
            return false;
        }
        try {
            pictureMapper.addPicture(picture);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + alias);
            log.info("文件存放在： " + path.toString());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件写入成功...");
            return true;
        } catch (IOException e) {
            System.out.println("在service中文件写入失败。。。。。。。。"+ UPLOAD_FOLDER);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addAllPicture(Picture picture) {
        System.out.println("添加本地图片到数据库");
        pictureMapper.addPicture(picture);
    }

    @Override
    public List<Picture> getAll() {
        return pictureMapper.getAll();
    }

    @Override
    public List<Picture> getAllToPage(int currentPage,int pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

               List<Picture> allItems = pictureMapper.getAll();   //全部图片
                int countNums = pictureMapper.pictureCount();            //总记录数
                PageBean<Picture> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(allItems);
                return pageData.getItems();

    }


    @Override
    @Transactional //在Spring Boot中推荐使用@Transactional注解来声明事务（写在service层函数上）。
    public void deleteAll() {
        pictureMapper.deleteAll();
        fileUtil.delAllFile(new File(UPLOAD_FOLDER));
    }

    @Override
    public void deleteById(Integer id) {
        pictureMapper.deleteById(id);
    }

    @Override
    public void updateByID(Picture picture) {
        pictureMapper.updateByID(picture);
    }

    @Override
    public Picture getPictureById(int pid) {
        log.debug("pid :" + pid);
      return pictureMapper.getPictureById(pid);
    }

    @Override
    public List<Picture> getPictureByType(int currentPage,int pageSize,String ptype) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);
        List<Picture> allItems = pictureMapper.getPictureByType(ptype);   //如 获取风景类型图片
        int countNums = pictureMapper.getCountByType(ptype);         //总记录数(某种类型图片总数)
        PageBean<Picture> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData.getItems();
    }
    //http://10.9.9.131:8080/staticphoto/d2d7516d-55c3-4fe0-aac4-61edd0f6d7d5.jpg
    @Override
    public String alterUrl(String alter){
        alter = alter.replace("=","");
        String d =alter.replace("%3A%2F%2F","://");
        List<Picture> pictures = pictureMapper.getAll();
        for(Picture p : pictures){
            int posi = p.getPurl().lastIndexOf(":");
            String u = p.getPurl().substring(posi);
            p.setPurl(d + u);
            pictureMapper.updateByID(p);
        }
        return "success";
    }
}
