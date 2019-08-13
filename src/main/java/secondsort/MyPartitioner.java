package secondsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区
 */
public class MyPartitioner extends Partitioner<CobKey, IntWritable>{
    public int getPartition(CobKey cobKey, IntWritable value, int numPartitions) {
        System.out.println("MyPartitioner");
        int year = cobKey.getYear();
        if(year>=1900 && year<=1930){
            return 0;
        }else if(year>1930 && year<=1960){
            return 1;
        }else {
            return 2;
        }
    }
}
