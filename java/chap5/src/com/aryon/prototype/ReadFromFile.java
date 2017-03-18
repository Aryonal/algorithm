package com.aryon.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by Dai on 2016/12/7.
 */
public class ReadFromFile {
    private final boolean                    INCLUDE = true;
    private final ArrayList<Character>       nums;
    private final ArrayList<Character>       sps;
    File            f;
    FileReader      fr;
    
    public ReadFromFile(File file) {
        this.f = file;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nums = new ArrayList<>();
        sps = new ArrayList<>();
        nums.addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', '-'));
        sps.addAll(Arrays.asList(' ', '.', '。', ',', '，', '\\', '/', '\'', '\"', '‘', '“', '!', '！', '?', '？', ':', '：', '(', ')', '（', '）'));
    }
    
    /**
     * @param flag 输出字符串中是否包含chr中字符
     *             if flag==INCLUDE
     *                  //输出连续的chr中的字符
     *             else
     *                  //输出字符不包含chr中的字符，并以之作为分隔符
     * @param chr  输入的字符数组，构成终端条件
     */
    private String next(boolean flag, ArrayList<Character> arr) {
        StringBuffer s = new StringBuffer();
        try {
            int tmp = fr.read();
            if (tmp == -1) return null;
            while (arr.contains((char)tmp) == flag && tmp != -1) {
                s.append((char)tmp);
                tmp = fr.read();
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close();
        }
        return null;
    }
    
    public int nextInt() {
        String s = next(INCLUDE, nums);
        if (s != null)
            return Integer.parseInt(s);
        else return -1;
    }
    
    @Deprecated
    public int nextInt(Character ... chr) {
        ArrayList<Character> arr = new ArrayList<>();
        arr.addAll(Arrays.asList(chr));
        String s = next(!INCLUDE, arr);
        if (s != null)
            return Integer.parseInt(s);
        else return -1;
    }
    
    public double nextDouble() {
        String s = next(INCLUDE, nums);
        if (s != null)
            return Double.parseDouble(s);
        else return -1;
    }
    
    public String nextString() {
        return next(!INCLUDE, sps);
    }
    
    public String nextString(Character ... chr) {
        ArrayList<Character> arr = new ArrayList<>();
        arr.addAll(Arrays.asList(chr));
        return next(!INCLUDE, arr);
    }
    
    public void close() {
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
