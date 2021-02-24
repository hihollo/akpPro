package com.xmm.akp.pojo;


public class Picture {
    private Integer pid;
    private String ptitle; // 图片名称
    private String psize; //图片高宽 如500*400
    private String ptype; //图片类型（如风景、人物、动漫。。。）
    private String psearch; // 搜索图片关键字
    private String purl; // 图片位置
    private String pyear; // 图片制作时间为何年
    private String plength; // 图片大小 如3M
    private String author; //图片作者


    public Integer getPid() {
        return pid;
    }

    public String getPsize() {
        return psize;
    }

    public void setPsize(String psize) {
        this.psize = psize;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPsearch() {
        return psearch;
    }

    public void setPsearch(String psearch) {
        this.psearch = psearch;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPyear() {
        return pyear;
    }

    public void setPyear(String pyear) {
        this.pyear = pyear;
    }

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pid=" + pid +
                ", ptitle='" + ptitle + '\'' +
                ", psize='" + psize + '\'' +
                ", ptype='" + ptype + '\'' +
                ", psearch='" + psearch + '\'' +
                ", purl=" + purl +
                ", pyear='" + pyear + '\'' +
                ", plength='" + plength + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
