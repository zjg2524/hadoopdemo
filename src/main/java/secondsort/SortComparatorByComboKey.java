package secondsort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortComparatorByComboKey extends WritableComparator {

    public SortComparatorByComboKey() {
        super(CobKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CobKey a1 = (CobKey) a;
        CobKey b1 = (CobKey) b;
        System.out.println("sortComparatorByComboKey......");
        return a1.compareTo(b1);
    }
}
