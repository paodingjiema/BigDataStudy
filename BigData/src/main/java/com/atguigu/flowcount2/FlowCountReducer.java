package com.atguigu.flowcount2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-03-31 22:04
 */
public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    FlowBean resultBean = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sum_upFlow = 0;
        long sum_downFlow = 0;

        // 1.遍历所用bean，将其中的上行流量，下行流量分别累加
        for (FlowBean value : values) {
            sum_upFlow += value.getUpFlow();
            sum_downFlow += value.getDownFlow();
        }

        // 2.获取输出的K,V
        resultBean.set(sum_upFlow, sum_downFlow);

        // 3.写出
        context.write(key, resultBean);
    }
}
