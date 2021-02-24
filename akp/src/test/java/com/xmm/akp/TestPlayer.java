/*
package com.xmm.akp;

import javazoom.jl.player.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

@SpringBootTest(classes = {TestPlayer.class})
public class TestPlayer {
    @Test
    public void player(){
        System.out.println("测试播放视频！");
        String music="E:/CloudMusic/MV/music.mp4";
        Runtime rn = Runtime.getRuntime();
        Process p = null;
        try {
            //D:\\app\\ThunderNetwork\\Thunder\\Program\\XMP\\XMP.exe "+music 中XMP.exe后要有一个空格
            //D:\\app\\ThunderNetwork\\Thunder\\Program\\XMP\\XMP.exe是指迅雷播放器
            p = rn.exec("D:\\app\\ThunderNetwork\\Thunder\\Program\\XMP\\XMP.exe "+music);
            System.out.println("播放完毕！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("播放失败!");
        }
    }

    @Test
    public void playMusic() {
         Player player=null;
          try {
            //声明一个File对象,指定音乐文件
            File mp3 = new File("E:\\CloudMusic\\Cam Kelley - Sketch Plane.mp3");
            //创建一个输入流
            FileInputStream fileInputStream = new FileInputStream(mp3);
            //创建一个缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //创建播放器对象，把文件的缓冲流传入进去
            player = new Player(bufferedInputStream);
            //调用播放方法进行播放
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("播放失败！！");
        }
    }
}
*/
