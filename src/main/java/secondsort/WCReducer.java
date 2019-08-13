package secondsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reducer
 */
public class WCReducer extends Reducer<CobKey, IntWritable, IntWritable, IntWritable>{
    /**
     * reduce
     */
    protected void reduce(CobKey key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int year = key.getYear();
        int temp = key.getTemp();
        for(IntWritable iw : values){
            year = key.getYear();
            temp = key.getTemp();
            System.out.println(year+"  ->  "+temp);
        }

        context.write(new IntWritable(year), new IntWritable(temp));
    }
}
