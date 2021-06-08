package com.moyao.demo.common;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomUtil {

    private static String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static FieldPosition HELPER_POSITION = new FieldPosition(0);
    private static NumberFormat numberFormat = new DecimalFormat("00000");
    private static AtomicInteger num = new AtomicInteger(0);

    //随时间增长ID生成
    public synchronized static  Long getNextId(){
        String numStr = getNum();
        return Long.parseLong(dateFormat.format(new Date()) + numStr);
    }

    public static String getRandomString(int length){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    private static String getNum(){
        int n = num.incrementAndGet();
        if(n > 99999){
            num.compareAndSet(n, 0);
            return getNum();
        }

        StringBuffer sb = new StringBuffer();
        numberFormat.format(num, sb, HELPER_POSITION);
        return sb.toString();
    }




}
