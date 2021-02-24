package com.xmm.akp.dao;

import com.xmm.akp.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface VideoMapper {
    Video getVideoById(Integer id); // 根据id查找视频
    List<Video> findAll();  //查找所有视频
    List<Video> search(@Param("keyWord") String keyWord); //根据关键字查视频
}
