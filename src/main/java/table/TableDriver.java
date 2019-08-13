package table;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import utils.DataUtils;

import java.io.File;

public class TableDriver {
	private static String FILEPATH = "C:\\Users\\Administrator\\Desktop\\temp\\demo01\\result2";
	public static void main(String[] args) throws Exception {
		if(new File(FILEPATH).exists()){

			DataUtils.deleteFile(FILEPATH);
		}
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		job.setJarByClass(TableDriver.class);

		job.setMapperClass(TableMapper.class);
		job.setReducerClass(TableReduce.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(TableBean.class);

		job.setOutputKeyClass(TableBean.class);
		job.setOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);

	}
}
