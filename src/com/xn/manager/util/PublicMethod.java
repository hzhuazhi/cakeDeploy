package com.xn.manager.util;


import org.apache.log4j.Logger;

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
//        List<Long> idList = list.stream().map(ChannelModel::getId).collect(Collectors.toList());// 获取某集合的某属性的集合
    }
}
