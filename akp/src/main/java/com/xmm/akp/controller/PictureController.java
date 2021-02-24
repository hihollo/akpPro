package com.xmm.akp.controller;

import com.xmm.akp.config.FileUtil;
import com.xmm.akp.entity.Picture;
import com.xmm.akp.service.Picture.PictureService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;


@RequestMapping("/picture")
@Controller
public class PictureController {
    private static Logger log= Logger.getLogger(PictureController.class);
    private String sepa = java.io.File.separator;
    @Autowired
    private FileUtil fileUtil;
    @Value("${prop.picture-url}")
    private String PICTURE_URL;
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String istest(){
        log.debug("this is just test!!");
        return "hello Mr.LI,I am snowman test";
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET) //get请求类似于mysql的select
    @ResponseBody
    public List<Picture> getAll(){
        List<Picture> pictures = new ArrayList<>();
        pictures = pictureService.getAll();
        log.info(pictures);
        return  pictures;
    }
    @RequestMapping(value = "/add",method= RequestMethod.POST) //post请求类似mysql的insert
    @ResponseBody
    public String add(@RequestParam MultipartFile file, @RequestParam ("ptitle") String ptitle, @RequestParam ("author")String author,
                      @RequestParam("psize") String psize,@RequestParam("ptype")String ptype,@RequestParam("psearch")String psearch,
                      @RequestParam("pyear")String pyear,@RequestParam("plength")String plength){
        File file1 = fileUtil.transferToFile(file);
        System.out.println("controller中path" + file1.getPath());
        Picture picture = new Picture();
        picture.setPtitle(ptitle);
        picture.setAuthor(author);
        picture.setPsize(fileUtil.getPictureSize(file1));
        picture.setPtype(ptype);
        picture.setPsearch(psearch);
        String fileSuffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String alias = UUID.randomUUID().toString()+fileSuffix; //以防图片重名，造成url多个
        picture.setPurl(PICTURE_URL + alias );
        picture.setPyear(pyear);
        picture.setPlength(fileUtil.getPictureLength(file1));
        boolean result=pictureService.addPicture(picture,file);
        if (result) {
            return "success";
        }else {
            return "failed";
        }
    }
    @RequestMapping(value = "/deleteAll",method = RequestMethod.DELETE) //delete请求相当于mysql的delete
    @ResponseBody
    public String deleteAll(){
        try{
            pictureService.deleteAll();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            log.warn("删除所有图片失败！！");
            return "false";
        }
    }

    @RequestMapping(value = "/deleteOne/{pid}",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOne(@PathVariable ("pid") Integer pid){

        System.out.println("删除id为:" + pid);
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
        log.debug("controller中根据id获取图片信息id为：" +pid );
        Picture picture =pictureService.getPictureById(pid);
        log.info(picture);
        return picture;
    }

    @RequestMapping(value = "/updateOne",method = RequestMethod.PUT) //put请求相当于mysql的update
    @ResponseBody
    public String update(@RequestBody Picture picture){
        log.debug("update");
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
    //@RequestMapping(value = "/picturePage",params = {"currentPage"},method = RequestMethod.GET)
    @ResponseBody
    public List<Picture> getAllPicturePage(@PathVariable("currentPage") Integer currentPage){ //当前页；每页个数
        int pageSize = 9; //每页个数
        log.debug("进入分页器");
        log.debug("currentPage: " + currentPage);
        return pictureService.getAllToPage(currentPage, pageSize);
    }

    @RequestMapping(value = "/getPictureByType",params = {"currentPage","ptype"},method = RequestMethod.GET)
    @ResponseBody
    public List<Picture> getPictureByType(Integer currentPage,String ptype){
        int pageSize = 6;
        log.debug("进入不同类型图片分页器");
        log.debug("currentPage: " + currentPage);
        return pictureService.getPictureByType(currentPage,pageSize,ptype);
    }

    @RequestMapping(value="/picturePath",method = RequestMethod.POST)
    @ResponseBody
    public String addPath(@RequestBody  String picturePath){
        System.out.println(sepa);
        log.debug(picturePath);
        picturePath = picturePath.replace("=","");
        picturePath = picturePath.replace("%5C%5C",",");
        picturePath = picturePath.replace("%3A",":");
        picturePath = picturePath.replace(",","\\\\");
        System.out.println(picturePath);
        log.debug("接收到的磁盘路径 "+ picturePath);
        try {
            fileUtil.addLocalPicture(picturePath); //"C:\\Users\\MING\\Pictures\\wallPaper"
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
    @RequestMapping(value = "/alter" ,method = RequestMethod.POST)
    @ResponseBody
    public String alter(@RequestBody String alterUrl){
        System.out.println("路径修改为：" + alterUrl);
        pictureService.alterUrl(alterUrl);
        return "success";
    }
}
