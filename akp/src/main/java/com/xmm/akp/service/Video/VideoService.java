package com.xmm.akp.service.Video;

import com.xmm.akp.entity.Video;

import java.util.List;

public interface VideoService {
    Video getVideoById(Integer id);
    List<Video> findAll();
    List<Video> search(String keyWord);
}
