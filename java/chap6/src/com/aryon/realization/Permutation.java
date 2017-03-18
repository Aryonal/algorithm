package com.aryon.realization;

/**
 * Created by Dai on 2017/1/28.
 */
public class Permutation {
    String      str;
    public Permutation(String s) {
        str = s;
    }
    private int min(String s, int from, int to) {
        int min = from;
        for(int i = from; i < to; i++) {
            if(min > s.charAt(i)) min = i;
        }
        return min;
    }
    private String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder();
        for(int c = 0; c < s.length(); c++) {
            if(c == i) sb.append(s.charAt(j));
            else if(c == j) sb.append(s.charAt(i));
            else sb.append(s.charAt(c));
        }
        return sb.toString();
    }
    private String reverse(String s, int i, int j) {
        StringBuilder sb = new StringBuilder();
        for(int c = 0; c < s.length(); c++) {
            if(c < i || c > j) sb.append(s.charAt(c));
            else sb.append(s.charAt(-(c-i-j)));
        }
        return sb.toString();
    }
    public String nextPermutation() {
        int i = 1;
        int l = str.length();
        while(str.charAt(l-i-1) > str.charAt(l-i)) {
            i++;
            if(i == l) return null;
        }
        int x = l-i-1;
        
        int j = l-1;
        for(; j > x; j--) {
            if(str.charAt(j) > str.charAt(x)) break;
        }
        str = swap(str, x, j);
        
        str = reverse(str, x+1, l-1);
        
        return str;
    }
    public Iterable<String> allPermutation() {
        return null;
    }
}
