package com.example.lenovo_pc.dailynews;

import java.util.Calendar;

public class PatternMatch {
    public int matchPattern(String source){
        Calendar date = Calendar.getInstance();
        int year=date.get(Calendar.YEAR);
        int i=0,j=0,index=0;
        boolean flag=false;
        String pattern;
        String s=source;
        int years[]={year,year-1,year-2,year-3};
        for (int y:years){
            pattern=Integer.toString(y)+"-";
            while(i < source.length() && j < pattern.length())
            {
                String s1=s.substring(i,i+1);
                String p=pattern.substring(j,j+1);
                if(s1.equals(p)){
                    ++i;  ++j;
                }
                else{
                    i=i-(j-1);        //回溯
                    j=0;
                }

            }
            if(j==pattern.length()) {
                flag = true;
                index=i-j;
                break; //退出for循环
            }//匹配成功
        }
        if(flag)
            return index;
        else
            return -1;

    }
}
