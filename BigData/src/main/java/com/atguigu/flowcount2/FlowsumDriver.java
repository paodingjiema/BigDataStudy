package com.atguigu.flowcount2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-03-31 22:21
 */
public class FlowsumDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"d:/input/input2","d:/output4"};

        // 1.获取配置信息，和job对象实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 2.指定本程序的jar包所在的本地路径
        job.setJarByClass(FlowsumDriver.class);

        // 3.指定本业务job要使用的Mapper/ Reducer 业务类
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        // 4.指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 5.指定最终输出的数据的k，v类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 6.指定job的输入原始文件所在的目录
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        // 7.将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn运行
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
