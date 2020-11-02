package com.xn.manager.util;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

/**
 * @author df
 * @Description: TODO(公共方法类)
 * @create 10:12 2018/9/12
 **/
public class PublicMethod{
    private static Logger log = Logger.getLogger(PublicMethod.class);




    /**
     * @Description: TODO(生成UUID)
     * @author df
     * @create 16:56 2018/9/18
     **/
    public static String assembleUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }



    public static String accuracy(double num, double total, int scale){
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(scale);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = num / total * 100;
        return df.format(accuracy_num)+"%";
    }






    public static void main(String[] args) throws Exception {
//        File  file =new File("D:\\tomcatyhk.txt");
//        String  str1 ="您尾号7032卡11月2日00:24工商银行收入(支付宝)1999.99元，余)19.99元，余额1,132.22元，对方户名：李冠朝";
//        System.out.println(str1.split("元").length);

        BufferedReader in = new BufferedReader(new FileReader("D:\\yhk4.txt"));
        String str;
        int  i=0;
        while ((str = in.readLine()) != null) {
            String []  dd=str.split("\\)");
            if(dd.length>=3){
                System.out.println(i+"==========="+str);
            }
            i++;

        }
        System.out.println(str);
//        List<Long> idList = list.stream().map(ChannelModel::getId).collect(Collectors.toList());// 获取某集合的某属性的集合
    }
}
