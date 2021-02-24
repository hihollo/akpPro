package com.xmm.akp.service.Video.impl;

import com.xmm.akp.dao.VideoMapper;
import com.xmm.akp.entity.Video;
import com.xmm.akp.service.Video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Video getVideoById(Integer id) {
        int i=id;
        return videoMapper.getVideoById(i);
    }

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public List<Video> search(String keyWord) {
        return videoMapper.search(keyWord);
    }
}
