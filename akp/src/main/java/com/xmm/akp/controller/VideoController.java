package com.xmm.akp.controller;

import com.xmm.akp.entity.Video;
import com.xmm.akp.service.Video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;
    @Autowired
    private VideoService videoService;

    @ResponseBody
    @RequestMapping(value="/getone",params = {"id"}) //根据id查询 请求参数和请求头表达式(http://localhost:8181/video/getone?id=3 获取id为3的信息)
   public Video getone(Integer id){
        Video video=videoService.getVideoById(id);
        System.out.println("===========");
        System.out.println(video);
        System.out.println("===========");
        return video;
    }

    @ResponseBody
    @RequestMapping("/findAll") // 查询所有
    public List<Video> findAll(){
        List<Video> videos=videoService.findAll();
        for (Video video : videos){
            System.out.println(video);
        }
        return videos;
    }

    @ResponseBody
    @RequestMapping(value = "/search",params = {"keyWord"}) // 根据关键字查询
    public List<Video> findByKey(String keyWord){
        List<Video> videos = videoService.search(keyWord);
        for (Video video : videos){
            System.out.println(video);
        }
        return videos;
    }

    @RequestMapping(value="/singlefile", method=RequestMethod.POST)
    @ResponseBody
    public Object singleFileUpload(MultipartFile file) {

        System.out.println("it is coming ...................");
        if (Objects.isNull(file) || file.isEmpty()) {
            System.out.println("文件为空");
            return "文件为空，请重新上传";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            System.out.println("文件存放在： " + path.toString());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            System.out.println("文件写入成功...");
            return "文件上传成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }



}
