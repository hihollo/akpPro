package com.xmm.akp.service.Picture;

import com.xmm.akp.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {
    boolean addPicture(Picture picture, MultipartFile file);
    void addAllPicture(Picture picture);
    List<Picture> getAll();
    List<Picture> getAllToPage(int currentPage,int pageSize);
    void deleteAll();
    void deleteById(Integer id);
    void updateByID(Picture picture);
    Picture getPictureById(int pid);
    List<Picture> getPictureByType(int currentPage,int pageSize,String ptype);
    String alterUrl(String alter);
}
