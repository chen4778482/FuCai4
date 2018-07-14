package com.example.fucai4.utlis;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 42224 on 2018/5/14.
 */

public class test {
    public static ArrayList<String> d(String a){
        String[] t = a.split("-");
        ArrayList<String> p = d(t[0],3,t[1]+t[2]);
        ArrayList<String> r = new ArrayList<String>();
        for(String s:p){
            char[] y=s.toCharArray();
            Arrays.sort(y);
            r.add(new String(y));
        }
        return r;
    }
    public static ArrayList<String> d(String a,int c,String l)
    {
        ArrayList<String> r = new ArrayList<String>();
        if(c==1){
            for(int i = 0; i < a.length(); i++) {
                r.add(String.valueOf(a.charAt(i)));
            }
            for(int i = 0; i < l.length(); i++) {
                r.add(String.valueOf(l.charAt(i)));
            }
            return r;
        }
        String t;
        ArrayList<String> b;
        for(int i = 0; i < a.length(); i++) {
            t=String.valueOf(a.charAt(i));
            b=d(a.substring(i+1),c-1,l);
            for(String u:b)
            {
                r.add(t+u);
            }
        }
        return r;
    }
    public static void main(String[] args) {
        System.out.println(d("789-1256-340"));
        System.out.println(d("789-1256-340").size());
    }
}
