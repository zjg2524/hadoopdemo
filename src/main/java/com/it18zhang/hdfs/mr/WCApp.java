package com.it18zhang.hdfs.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.util.Arrays;

/**
 *
 */
public class WCApp {
    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(args));
        Configuration conf = new Configuration();

//        conf.set("fs.defaultFS", "file:///");

        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("WCApp");                        //作业名称
        job.setJarByClass(WCApp.class);                 //搜索类
//        job.setInputFormatClass(TextInputFormat.class); //设置输入格式

        //设置输出格式类
        //job.setOutputFormatClass(SequenceFileOutputFormat.class);

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //设置最大切片数
        //FileInputFormat.setMaxInputSplitSize(job,13);
        //最小切片数
        //FileInputFormat.setMinInputSplitSize(job,1L);

        //设置分区类
        job.setPartitionerClass(MyPartitioner.class);   //设置自定义分区

        //设置合成类
        job.setCombinerClass(WCReducer.class);          //设置combiner类

        job.setMapperClass(WCMapper.class);             //mapper类
        job.setReducerClass(WCReducer.class);           //reducer类

        job.setNumReduceTasks(3);                       //reduce个数

        job.setMapOutputKeyClass(Text.class);           //
        job.setMapOutputValueClass(IntWritable.class);  //

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);     //

        job.waitForCompletion(true);
    }
}
