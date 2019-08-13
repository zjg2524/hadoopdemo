package mysql;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer
 */
public class WCReducer extends Reducer<Text, IntWritable, UserRecordWritable, NullWritable>{
    /**
     * reduce
     */
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0 ;
        for(IntWritable iw : values){
            count = count + iw.get() ;
        }
        UserRecordWritable userRecordWritable = new UserRecordWritable();
        userRecordWritable.setWord(key.toString());
        userRecordWritable.setWordCount(count);
        context.write(userRecordWritable, null);
    }
}
