package com.aryon.realization;

/**
 * Created by Dai on 2017/2/3.
 */
public class ListFactory<T> {
    public static void shuffle() {
        //
    }
    
    public static int match(String str, String s) {
        return match(str, s, 0);
    }
    public static int match(String str, String s, int fr) {
        int[] next = genNext(s);
        int i = fr, j = 0;
        int L = str.length(), l = s.length();
        while(i < L && j < l) {
            if(j == -1 || s.charAt(j) == str.charAt(i)) {
                i++; j++;
            } else {
                j = next[j];
            }
        }
        if(j == l) return i-j;
        return -1;
    }
    private static int[] genNext(String s) {
        int[] next = new int[s.length()];
        int l = s.length(), k = -1, j = 0;
        next[0] = -1;
        while(j < l-1) {
            if(k == -1 || s.charAt(j) == s.charAt(k)) {
                k++; j++;
                if(s.charAt(j) != s.charAt(k)) next[j] = k;
                else next[j] = next[k];
            } else {
                k = next[k];
            }
        }
    
        System.out.print("list:  ");
        for(int it = 0; it < s.length(); it++) System.out.print(s.charAt(it) + " ");
        System.out.println();
        System.out.print("next: ");
        for(int it : next) System.out.print(it + " ");
        System.out.println();
        return next;
    }
}
