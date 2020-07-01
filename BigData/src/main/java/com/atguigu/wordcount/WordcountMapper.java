package com.atguigu.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-03-24 17:02
 */
public class WordcountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
    Text k = new Text();
    IntWritable v = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1.获取一行
        String line = value.toString();

        //  2.切割
        String[] words = line.split(" ");

        // 3.输出
        for (String word : words) {
            k.set(word);
            context.write(k,v);
        }
    }
}