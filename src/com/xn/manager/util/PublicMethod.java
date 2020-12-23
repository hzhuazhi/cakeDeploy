package com.xn.manager.util;


import com.xn.common.util.StringUtil;
import com.xn.manager.model.AdminWithdrawModel;
import org.apache.commons.lang.StringUtils;
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


    /**
     * @Description: 组装查询提现汇总的查询条件的方法
     * @param id - 主键ID
     * @param orderNo - 订单号
     * @param outTradeNo - 第三方订单号
     * @param orderStatus - 订单状态：1初始化，2超时，3质疑，4成功
     * @param withdrawType - 提现订单类型：1利益者提现，2卡商提现，3渠道提现
     * @param channelId - 渠道主键ID
     * @param channelType - 渠道类型：0初始化，1代收，2大包，3代付；订单类型是渠道提现时，这里做自动分派是需要用到
     * @param outType - 指派由谁进行转账给提现人：1卡商，2中转站，3平台
     * @param merchantId - 指派给的卡商ID：对应表tb_fr_merchant的主键ID；假如指派out_type=1，则此字段不允许为空
     * @param checkStatus - 审核状态：1初始化，2审核收款失败，3审核收款成功
     * @param workType - 补充数据的类型：1初始化，2补充数据失败（其它原因等..），3补充数据成功；这里是派单状态
     * @param curday - 创建日期
     * @return com.cake.task.master.core.model.withdraw.WithdrawModel
     * @author yoko
     * @date 2020/12/2 16:33
     */
    public static AdminWithdrawModel assembleWithdrawQuery(long id, String orderNo, String outTradeNo, int orderStatus, int withdrawType, long channelId, int channelType,
                                                           int outType, long merchantId, int checkStatus, int workType, int curday){
        AdminWithdrawModel resBean = new AdminWithdrawModel();
        if (id > 0){
            resBean.setId(id);
        }
        if (!StringUtils.isBlank(orderNo)){
            resBean.setOrderNo(orderNo);
        }
        if (!StringUtils.isBlank(outTradeNo)){
            resBean.setOutTradeNo(outTradeNo);
        }
        if (orderStatus > 0){
            resBean.setOrderStatus(orderStatus);
        }
        if (withdrawType > 0){
            resBean.setWithdrawType(withdrawType);
        }
        if (channelId > 0){
            resBean.setChannelId(channelId);
        }
        if (channelType > 0){
            resBean.setChannelType(channelType);
        }
        if (outType > 0){
            resBean.setOutType(outType);
        }
        if (merchantId > 0){
            resBean.setMerchantId(merchantId);
        }
        if (checkStatus > 0){
            resBean.setCheckStatus(checkStatus);
        }
        if (workType > 0){
            resBean.setWorkType(workType);
        }
        if (curday > 0){
            resBean.setCurday(curday);
        }
        return resBean;
    }


    /**
     * @Description: check卡商是否有足够的余额来分配此提现订单
     * <p>
     *     这里有一个计算公式：要进行分配的提现金额 + 卡商已经分配但是未处理的提现金额 < 卡商现有余额
     * </p>
     * @param balance - 余额
     * @param orderMoney - 要分配的下发的金额
     * @param withdrawMoney - 已经分配完毕但是还未操作的提现下发金额
     * @return boolean
     * @author yoko
     * @date 2020/12/2 16:47
     */
    public static boolean checkMerchantMoney(String balance, String orderMoney, String withdrawMoney){
        if (StringUtils.isBlank(balance)){
            return false;
        }
        String addMoney = StringUtil.getBigDecimalAdd(orderMoney, withdrawMoney);
        return StringUtil.getBigDecimalSubtract(balance, addMoney);
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
