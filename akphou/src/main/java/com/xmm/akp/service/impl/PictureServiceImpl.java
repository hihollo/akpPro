package com.xmm.akp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmm.akp.config.FileUtil;
import com.xmm.akp.dao.PictureDao;
import com.xmm.akp.pojo.Picture;
import com.xmm.akp.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class PictureServiceImpl implements PictureService {
    private PictureDao pictureDao;

    @Autowired
    private FileUtil fileUtil;


    public void setPictureDao(PictureDao pictureDao){
        this.pictureDao = pictureDao;
    }

    @Override
    public List<Picture> getAll() {
        //System.out.println("进入service");
        List<Picture>  pictures=pictureDao.getAll();
        //System.out.println(pictures);
        return pictures;
    }

    @Override
    public int addPicture(MultipartFile file, String ptitle, String author, String psize, String ptype, String psearch, String pyear, String plength) {
        String mFileName=file.getOriginalFilename();
        //System.out.println("后缀名：");
        String suffixName = mFileName.substring(mFileName.indexOf("."));
        System.out.println(suffixName);
        String photoAlias = UUID.randomUUID().toString()+suffixName; //以防图片重名，造成url多个
        fileUtil.keeepPhoto(file,photoAlias);//将图片写入磁盘
        Picture picture=fileUtil.getPhotoInfo(ptitle,author,psize,ptype,psearch,pyear,plength,photoAlias);
        int affectLine=pictureDao.addPicture(picture);
        return affectLine;
    }

    @Override
    public int deleteAll() {
        fileUtil.deleteAllFile();
        return pictureDao.deleteAll();
    }

    @Override
    public int deleteById(Integer id) {
        Picture picture=pictureDao.getPictureById(id);
        String aliasName = picture.getPurl().substring(picture.getPurl().lastIndexOf("/"));
        fileUtil.deleteFileByName(aliasName);//删除磁盘中的图片
        pictureDao.deleteById(id);//删除数据库中该图片信息
        return 0;
    }

    @Override
    public int updateByID(Picture picture) {
       return pictureDao.updateByID(picture);
    }

    @Override
    public Picture getPictureById(int pid) {
        return pictureDao.getPictureById(pid);
    }

    @Override
    public List<Picture> getPictureByType(String ptype) {
        return pictureDao.getPictureByType(ptype);
    }




}
