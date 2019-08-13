package table;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableReduce extends Reducer<Text, TableBean, TableBean, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<TableBean> values, Context context)
			throws IOException, InterruptedException {
		TableBean pdbean = new TableBean();
		ArrayList<TableBean> orderBeans = new ArrayList<>();
		for (TableBean bean : values) {

			if (0 == bean.getFlag()) {
				// 1001 1
				// 1001 1
				TableBean orBean = new TableBean();

				try {
					BeanUtils.copyProperties(orBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}

				orderBeans.add(orBean);

			} else {
				try {
					BeanUtils.copyProperties(pdbean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for (TableBean bean : orderBeans) {
			bean.setName(pdbean.getName());
			System.out.println("bean -> "+bean);
			context.write(bean, NullWritable.get());
		}
	}
}
