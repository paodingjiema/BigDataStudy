package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;
import sun.awt.ConstrainableGraphics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author shkstart
 * @create 2020-03-20 22:30
 *
 *自定义hdfs的IO操作
 */
public class HdfsClient_IO {
    // 把d盘的banhua.txt文件上传到hdfs根目录
    @Test
    public void testPutFileToHdfs() throws URISyntaxException, IOException, InterruptedException {
        // 1.配置文件
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration, "root");

        // 3.创建输入流
        FileInputStream fis = new FileInputStream("d:/banhua.txt");

        // 3.创建输出流
        FSDataOutputStream fos =
                fs.create(new Path("/banhua.txt"));

        // 4流的对拷
        IOUtils.copyBytes(fis,fos,configuration);

        // 5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();

    }

    // 从hdfs上把文件下载到本地
    public void testGetFileFromHds() throws URISyntaxException, IOException, InterruptedException {
        // 1.获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration, "root");

        // 2.获取输入流
        FSDataInputStream fis = fs.open(new Path("/banhua.txt"));

        // 3.获取输出流
        FileOutputStream fos = new FileOutputStream("e:/");

        // 4.流的对拷
        IOUtils.copyBytes(fis,fos,configuration);

        // 5.关闭资源
        IOUtils.closeStream(fis);
        IOUtils.closeStream(fos);
        fs.close();











    }

    //  分块读取HDFS上的大文件，比如根目录下的/hadoop-2.7.2.tar.gz
    //  读取第一块
    @Test
    public void readFileSeek1() throws URISyntaxException, IOException, InterruptedException {
        // 1.获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration, "root");

        // 2.获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-2.7.2.tar.gz"));

        // 2.获取输出流
        FileOutputStream fos = new FileOutputStream("d:/hadoop-2.7.2.tar.gz");

        // 3.读入文件
        byte[] buf = new byte[1024];
        for(int i = 0; i < 1024*128;i++){
            fis.read(buf);
            fos.write(buf);
        }

        // 5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();


    }

    // 读取第二块
    public void readFileSeek2() throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration,"root");

        FSDataInputStream fis = fs.open(new Path("/hadoop-2.72.tar.gz"));

        //定位1输入数据位置
        fis.seek(1024*1024*128);

        FileOutputStream fos = new FileOutputStream("e:/hadoop-2.7.2.tar.gz.part2");

        // 流的对拷
        IOUtils.copyBytes(fis,fos,configuration);

        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();

    }
}
