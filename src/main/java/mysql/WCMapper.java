package mysql;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * WCMapper
 */
public class WCMapper extends Mapper<LongWritable, UserRecordWritable, Text, IntWritable>{
    @Override
    protected void map(LongWritable key, UserRecordWritable value, Context context) throws IOException, InterruptedException {
        System.out.println("key -> "+key);
        System.out.println("value -> "+value);
        context.write(new Text(value.getName()), new IntWritable(1));
    }
}
