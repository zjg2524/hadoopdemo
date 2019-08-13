package com.it18zhang.hdfs;

import java.util.Arrays;

public class CommonTest {

    public static void main(String[] args) {
        String regex = "\\s+";
        String src = "1991     34";
        String[] split = src.split(regex);
        System.out.println(Arrays.toString(split));
    }
}
