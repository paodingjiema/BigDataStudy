package com.atguigu.flowcount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-03-30 15:32
 */
public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
    //    1	13736230513	192.196.100.1	www.atguigu.com	2481	24681   200

    // 输出时的k，v；
    Text k = new Text();
    FlowBean v = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1.获取一行
        String line = value.toString();

        // 2.切割字段
        String[] fields = line.split("\t");

        // 3.获取输出的k值
        String phoneNum = fields[1];
        k.set(phoneNum);


        // 4.获取输出时v的值
            // 获取上行流量和下行流量
        long upFlow = Long.parseLong(fields[fields.length-3]);
        long dowFlow = Long.parseLong(fields[fields.length-2]);
        v.set(upFlow,dowFlow);

        // 5.写出
        context.write(k,v);

    }
}
