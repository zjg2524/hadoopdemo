package table;

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
public class TableBean implements WritableComparable<TableBean> {
    private String oid;
    private String pid;
    private int mount;

    private String name;
    private int flag;


    @Override
    public int compareTo(TableBean o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(oid);
        dataOutput.writeUTF(pid);
        dataOutput.writeInt(mount);

        dataOutput.writeUTF(name);
        dataOutput.writeInt(flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.oid = dataInput.readUTF();
        this.pid = dataInput.readUTF();
        this.mount = dataInput.readInt();

        this.name = dataInput.readUTF();
        this.flag = dataInput.readInt();
    }

    @Override
    public String toString() {
        return
                oid+"\t"+mount +
                "\t" + name ;
    }
}
