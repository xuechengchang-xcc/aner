package com.wangzaiplus.test.tes;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @date ：2020/2/13 21:37
 * @Author : 安儿
 * Description: TODO
 */
public class dingshi {

    public static void main(String[] args) {
        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(500);
                        System.out.println("1秒执行一次");
                        String dangqian=timeStamp2Date(timeStamp(),"yyyy-MM-dd HH:mm");
                        String reuwu=timeStamp2Date(date2TimeStamp("2020-02-14 18:12","yyyy-MM-dd HH:mm"),"yyyy-MM-dd HH:mm");
                        if (dangqian.equals(reuwu)){
                            System.out.println("++++++++++++"+true);
                            System.out.println("对比成功!");
                        }
                        else{
                            System.out.println("++++++++++++++"+false);
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param date 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time);
        return t;
    }
}
