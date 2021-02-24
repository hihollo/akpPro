package com.xmm.akp.dao;


import com.xmm.akp.pojo.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PictureDao {
    int addPicture(Picture picture); //添加图片
    List<Picture> getAll(); //获取所有图片
    int deleteAll(); //删除所有图片
    int deleteById(Integer id); //根据id删除图片
    int updateByID(Picture picture);//更新图片信息
    Picture getPictureById(int pid);
    Integer pictureCount();//获取所用图片数量
    Integer getCountByType(@Param("ptype") String ptype); //获取某种类型图片数量
    List<Picture>getPictureByType(@Param("ptype") String ptype);

}
