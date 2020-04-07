package com.atguigu.flowcount2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-03-31 21:47
 */
public class FlowCountMapper extends Mapper<LongWritable,Text,Text,FlowBean> {
    // 定义输出时看K,V对
    Text k = new Text();
    FlowBean v ;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1.获取一行
        String line = value.toString();

        // 2.切割字段
        String[] fields = line.split("\t");

        // 3.获得需要的输出K , V 对
        // 3.1获得输出的k
        String phoneNum = fields[1];
        k.set(phoneNum);

        // 3.2获得输出的v
        long upFlow = Long.parseLong(fields[fields.length-3]);
        long downFlow = Long.parseLong(fields[fields.length-2]);
        v = new FlowBean(upFlow,downFlow);

        // 4写出
        context.write(k,v);


    }
}


























































