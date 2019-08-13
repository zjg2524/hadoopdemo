package mysql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;
import org.apache.hadoop.mapred.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.Job;

/**
 *
 */
public class WCApp {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("MySQLApp");                        //作业名称
        job.setJarByClass(WCApp.class);                 //搜索类

        //配置数据库信息
        String driverclass = "com.mysql.jdbc.Driver" ;
        String url = "jdbc:mysql://localhost:3306/test" ;
        String username= "root" ;
        String password = "root" ;
        //设置数据库配置
        DBConfiguration.configureDB(job.getConfiguration(),driverclass,url,username,password);
        //设置数据输入内容
        DBInputFormat.setInput(job,UserRecordWritable.class,"select * from user","select count(*) from user");

        //
        DBOutputFormat.setOutput(job,"stats","word","c");

        //设置分区类
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
