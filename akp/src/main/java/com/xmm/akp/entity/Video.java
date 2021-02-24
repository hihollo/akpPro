package com.xmm.akp.entity;


import javax.persistence.*;

@Entity
@Table(name="t_video")//为数据库中的这个表起一个名字，如果不用@Table起名字，默认表名是该类的类名
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer vid; //视频id，主键

    @Column(unique =true)
    private String vtitle; // 视频名称
    private String vsearch; // 搜索视频关键字
    private String vlocaltion; // 视频位置
    private String vyear; // 视频录制时间为何年
    private String vlength; // 视频长度
    private String vtype; //视频类型（视频讲的是哪种内容）
    private String vdesc; //视频的简单描述

    public String getVsearch() {
        return vsearch;
    }

    public void setVsearch(String vsearch) {
        this.vsearch = vsearch;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getVlocaltion() {
        return vlocaltion;
    }

    public void setVlocaltion(String vlocaltion) {
        this.vlocaltion = vlocaltion;
    }

    public String getVyear() {
        return vyear;
    }

    public void setVyear(String vyear) {
        this.vyear = vyear;
    }

    public String getVlength() {
        return vlength;
    }

    public void setVlength(String vlength) {
        this.vlength = vlength;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getVdesc() {
        return vdesc;
    }

    public void setVdesc(String vdesc) {
        this.vdesc = vdesc;
    }

    @Override
    public String toString() {
        return "Video{" +
                "vid=" + vid +
                ", vtitle='" + vtitle + '\'' +
                ", vsearch='" + vsearch + '\'' +
                ", vlocaltion='" + vlocaltion + '\'' +
                ", vyear='" + vyear + '\'' +
                ", vlength='" + vlength + '\'' +
                ", vtype='" + vtype + '\'' +
                ", vdesc='" + vdesc + '\'' +
                '}';
    }
}
