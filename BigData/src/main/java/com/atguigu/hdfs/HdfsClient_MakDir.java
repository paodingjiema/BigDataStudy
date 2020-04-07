package com.atguigu.hdfs;


import com.sun.deploy.panel.ITreeNode;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * @author shkstart
 * @create 2020-03-18 20:36
 * 在hdfs上创建文件夹
 */
public class HdfsClient_MakDir {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // 1.获取文件系统
        Configuration configuration = new Configuration();

        // 2.配置在集群上运行
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration,
                "root");

        // 3.具体操作// 创建目录
        fs.mkdirs(new Path("/1108/daxian/banzhang"));
        fs.mkdirs(new Path("/1108/daxian/ban"));
        // 4.关闭资源
        fs.close();
    }

    // 从本地上传文件到hdfs上
    @Test
    public void testCopyFromLocalFile() throws URISyntaxException, IOException, InterruptedException {
        // 1.获取文件系统
        Configuration configuration = new Configuration();

        // 2.设置副本数为2
        configuration.set("dfs.replication", "2");

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000")
                , configuration, "root");

        // 3.上传文件
        fs.copyFromLocalFile(new Path("d:/lishangshang.txt"), new Path("/banhua.txt"));
    }

    // hdfs文件下载
    @Test
    public void testCopyToLocalFile() throws URISyntaxException, IOException, InterruptedException {

        // 1.设置配置信息
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration,
                "root");

        // 3.进行文件下载
        fs.copyToLocalFile(false, new Path("/banhua.txt"),
                new Path("d:/ilikebanhua.txt"), true);

        // 4.关闭资源
        fs.close();
    }

    // hdfs文件删除
    @Test
    public void testDelet() throws URISyntaxException, IOException, InterruptedException {
        // 1.设置配置信息
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration, "root");

        // 3.执行操作
        fs.delete(new Path("/1108/"), true);
    }

    // hdfs文件名更改
    @Test
    public void testRename() throws URISyntaxException, IOException, InterruptedException {
        // 1.创建配置信息
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration, "root");

        // 3.修改文件名
        fs.rename(new Path("/banhua.txt"), new Path("/banzhang.txt"));

        // 4.关闭资源
        fs.close();
    }

    // hdfs查看文件详情
    @Test
    public void testListFiles() throws URISyntaxException, IOException, InterruptedException {
        // 1.设置配置信息
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "root");

        // 3.获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();
            // 输出文件名
            System.out.println(status.getPath());
            System.out.println(status.getPath().getName());

            // 输出长度
            System.out.println(status.getLen());

            // 输出权限
            System.out.println(status.getPermission());

            // 输出分组
            System.out.println(status.getGroup());

            // 获取存储块的信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("-------------------------------");
        }
        // 4.关闭资源
        fs.close();
    }

    // 查看hdfs上文件2
    @Test
    public void testListFile() throws URISyntaxException, IOException, InterruptedException {

        // 1.配置，和获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs//hadoop102:9000"), configuration, "root");

        // 2.获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles =
                fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();

            // 文件名
            System.out.println(status.getPath().getName());
            // 长度
            System.out.println(status.getLen());
            // 权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());
            // 块的信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }

    }


    // 查看hdfs上文件详情
    @Test
    public void testCheckHdfsFiles() throws URISyntaxException, IOException, InterruptedException {
        // 1.设置配置文件
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),
                configuration, "root");

        // 3.具体的操作
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus status = listFiles.next();

            // 文件名称
            System.out.println(status.getPath().getName());

            // 长度
            System.out.println(status.getLen());

            // 权限
            System.out.println(status.getPermission());

            // 分组
            System.out.println(status.getGroup());

            // 获取存储块的信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("--------------------");
        }
        // 4.关闭文件
        fs.close();
    }

    // 判断是文件还是文件夹
    @Test
    public void testIsFile() throws URISyntaxException, IOException, InterruptedException {
        // 1.设置配置文件
        Configuration configuration = new Configuration();

        // 2.获取文件系统
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "root");

        // 3.具体的操作
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("f:" + fileStatus.getPath().getName());
            } else {
                System.out.println("d:" + fileStatus.getPath().getName());
            }
        }
        // 4.关闭资源
        fs.close();
    }


}























