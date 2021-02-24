package com.xmm.akp.service;

import com.github.pagehelper.PageInfo;
import com.xmm.akp.pojo.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {
    List<Picture> getAll();
    int addPicture( MultipartFile file, String ptitle, String author, String psize, String ptype,String psearch, String pyear, String plength);

    int deleteAll();
    int deleteById(Integer id);
    int updateByID(Picture picture);

    Picture getPictureById(int pid);

    List<Picture> getPictureByType(String ptype);

}
