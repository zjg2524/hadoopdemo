package table;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean>{
	TableBean bean = new TableBean();
	Text k = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		FileSplit inputSplit = (FileSplit) context.getInputSplit();
		String name = inputSplit.getPath().getName();
		
		String line = value.toString();
		if(line.trim().length()==0){
			return ;
		}
		if (name.startsWith("order")) {
			String[] fields = line.split("\\s+");
			
			bean.setOid(fields[0]);
			bean.setPid(fields[1]);
			bean.setMount(Integer.parseInt(fields[2]));
			
			bean.setName("");
			bean.setFlag(0);
			
			k.set(fields[1]);
			
		}else {

			String[] fields = line.split("\\s+");
			
			bean.setOid("");
			bean.setPid(fields[0]);
			bean.setMount(0);
			bean.setName(fields[1]);
			bean.setFlag(1);
			
			k.set(fields[0]);
		}
		
		context.write(k, bean);
	}

}
