package utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DataUtils {

    private static final Random random = new Random();
    private static String PER = "19";
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10000000; i++) {
            int year = randomYear();
            int temp = randomTemp();

            IOUtils.write(year+"  "+temp+"\r\n",
                    new FileOutputStream("C:\\Users\\Administrator\\Desktop\\temp\\demo01\\data\\data1.txt", true));
        }


    }

    public static int randomYear(){

        int i = random.nextInt(100);
        if(i>=0 && i<=9){

            return Integer.parseInt(PER + "0" + i);
        }
        int year = Integer.parseInt(PER + i);
        return year;
    }

    public static int randomTemp(){
        return random.nextInt(200);
    }


    public static void deleteFile(String filePath) throws IOException {
        FileUtils.forceDelete(new File(filePath));
    }
}
