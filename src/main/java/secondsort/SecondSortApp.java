package secondsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;
import org.apache.hadoop.mapred.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import utils.DataUtils;

import java.io.File;

/**
 *
 */
public class SecondSortApp {
    private static String FILEPATH = "C:\\Users\\Administrator\\Desktop\\temp\\demo01\\result01";
    public static void main(String[] args) throws Exception {
        if(new File(FILEPATH).exists()){

            DataUtils.deleteFile(FILEPATH);
        }
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJobName("secondSort");
        job.setJarByClass(SecondSortApp.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path("C:\\Users\\Administrator\\Desktop\\temp\\demo01\\data"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\Administrator\\Desktop\\temp\\demo01\\result01"));
        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);
        job.setPartitionerClass(MyPartitioner.class);
        job.setSortComparatorClass(SortComparatorByComboKey.class);
        job.setGroupingComparatorClass(GroupByYear.class);
        job.setNumReduceTasks(3);
        job.setMapOutputKeyClass(CobKey.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }
}
