package secondsort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CobKey implements WritableComparable<CobKey> {
    private int year;
    private int temp;

    @Override
    public int compareTo(CobKey o) {
        if(this.getYear() == o.getYear()){
            return this.temp - o.getTemp();
        }else {

            return this.year - o.getYear();
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(year);
        dataOutput.writeInt(temp);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.year = dataInput.readInt();
        this.temp = dataInput.readInt();
    }
}
