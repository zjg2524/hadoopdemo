package secondsort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupByYear extends WritableComparator {

    public GroupByYear() {
        super(CobKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        CobKey a1 = (CobKey) a;
        CobKey b1 = (CobKey) b;
        return a1.getYear() - b1.getYear();
    }
}
