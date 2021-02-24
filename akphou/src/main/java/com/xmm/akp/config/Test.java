package com.xmm.akp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
    public static void main(String [] args){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream(new File("D:\\Java\\apache-tomcat-9.0.37-windows-x64\\apache-tomcat-9.0.37\\webapps\\staticphoto\\7e8354cc-576f-4df5-81c7-194d01185d52.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                System.out.println("关闭成功");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("关闭失败");
            }
        }
    }
}
