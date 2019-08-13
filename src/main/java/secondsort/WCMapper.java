package secondsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * WCMapper
 */
public class WCMapper extends Mapper<LongWritable, Text, CobKey, IntWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\\s+");
        System.out.println(Arrays.toString(split));
        CobKey cobKey = new CobKey(Integer.parseInt(split[0]),
                Integer.parseInt(split[1]));
        context.write(cobKey, new IntWritable(Integer.parseInt(split[1])));

    }
}
