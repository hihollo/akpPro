package com.xmm.akp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmm.akp.config.FileUtil;
import com.xmm.akp.pojo.Picture;
import com.xmm.akp.service.PictureService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@CrossOrigin
@Controller
@RequestMapping("/picture")
public class PictureController {

    static Logger logger = Logger.getLogger(PictureController.class);
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "just test!";
    }

    @RequestMapping(value = "/getAll",method= RequestMethod.GET)
    @ResponseBody
    public List<Picture> getAll(){
        List<Picture> pictures = pictureService.getAll();
        //System.out.println("图片总数：" + pictures.size());
        return pictures;
    }

    @RequestMapping(value = "/add",method= RequestMethod.POST) //post请求类似mysql的insert
    @ResponseBody
    public String add(@RequestParam MultipartFile file, @RequestParam ("ptitle") String ptitle, @RequestParam ("author")String author,
                      @RequestParam("psize") String psize, @RequestParam("ptype")String ptype, @RequestParam("psearch")String psearch,
                      @RequestParam("pyear")String pyear, @RequestParam("plength")String plength){

        pictureService.addPicture(file, ptitle, author, psize,  ptype, psearch, pyear, plength);
        return "ok";
    }
    @RequestMapping(value = "/addMore",method= RequestMethod.POST) //post请求类似mysql的insert
    @ResponseBody
    public String add(@RequestParam("file0") MultipartFile file0,@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2,
                      @RequestParam("file3") MultipartFile file3,@RequestParam("file4") MultipartFile file4,@RequestParam("ptype")String ptype ){

        logger.debug("多文件上传。。。。。");

           if(file0!=null){
               pictureService.addPicture(file0, "null", "null", "null",  ptype, "null", "null", "null");
           }
        if(file1!=null){
            pictureService.addPicture(file1,"null", "null", "null",  ptype, "null", "null", "null");
        }
        if(file2!=null){
            pictureService.addPicture(file2,"null", "null", "null",  ptype, "null", "null", "null");
        }
        if(file3!=null){
            pictureService.addPicture(file3,"null", "null", "null",  ptype, "null", "null", "null");
        }
        if(file4!=null){
            pictureService.addPicture(file4, "null", "null", "null",  ptype, "null", "null", "null");
        }

        return "addMore ok";
    }

    @RequestMapping(value = "/deleteAll",method = RequestMethod.DELETE) //delete请求相当于mysql的delete
    @ResponseBody
    public String deleteAll(){
        try{
            pictureService.deleteAll();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value = "/deleteOne/{pid}",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOne(@PathVariable ("pid") Integer pid){
        try{
            pictureService.deleteById(pid);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value = "/getOne/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public Picture getOne(@PathVariable int pid){
        Picture picture =pictureService.getPictureById(pid);
        return picture;
    }
    @RequestMapping(value = "/updateOne",method = RequestMethod.PUT) //put请求相当于mysql的update
    @ResponseBody
    public String update(@RequestBody Picture picture){
        System.out.println(picture);
        try{
            pictureService.updateByID(picture);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value = "/picturePage/{currentPage}",method = RequestMethod.GET)
    @ResponseBody
    public List<Picture> getAllPicturePage(@PathVariable("currentPage") Integer currentPage){ //当前页；每页个数
        //logger.debug("进入分页器");
        //logger.debug("currentPage: " + currentPage);
        PageHelper.startPage(currentPage, 6);
        List<Picture> pictures = new ArrayList<>();
        pictures= pictureService.getAll();
        PageInfo<Picture> pi = new PageInfo<>(pictures);
        return pictures;
    }
    @RequestMapping(value = "/getPictureByType",params = {"currentPage","ptype"},method = RequestMethod.GET)
    @ResponseBody
    public List<Picture> getPictureByType(Integer currentPage, String ptype){
       //logger.debug("进入不同类型图片分页器");
        //logger.debug("currentPage: " + currentPage);
        PageHelper.startPage(currentPage, 6);
        List<Picture> pictures = new ArrayList<>();
        pictures= pictureService.getPictureByType(ptype);
        PageInfo<Picture> pi = new PageInfo<>(pictures);
        return pictures;
    }
}
