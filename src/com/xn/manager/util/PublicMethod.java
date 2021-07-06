package com.xn.manager.util;


import com.xn.common.util.DateUtil;
import com.xn.common.util.StringUtil;
import com.xn.manager.model.AdminWithdrawModel;
import com.xn.manager.model.OrderOutModel;
import com.xn.manager.model.excel.OrderOutExcelInModel;
import com.xn.manager.model.replacepay.ReplacePayGainResultModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

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


    /**
     * @Description: 根据导出金额来筛选要导出的代付订单集合
     * @param orderOutList
     * @param excelMoney
     * @return
     * @Author: yoko
     * @Date 2021/7/4 15:15
    */
    public static Map<String, Object> getOrderOutByExcelMoneyList(List<OrderOutModel> orderOutList, String excelMoney){
        Map<String, Object> map = new HashMap<>();
        List<OrderOutModel> resList = new ArrayList<>();
        String totalMoney = "0.00";
        String totalOrderSumMoney = "0.00";
        for (OrderOutModel orderOutModel : orderOutList){
            totalOrderSumMoney = totalMoney;
            totalMoney = StringUtil.getBigDecimalAdd(totalMoney, orderOutModel.getOrderMoney());
            boolean flag = StringUtil.getBigDecimalSubtract(excelMoney, totalMoney);
            if (flag){
                totalOrderSumMoney = totalMoney;
                resList.add(orderOutModel);
            }else {
                break;
            }
        }
        if (resList != null && resList.size() > 0){
            map.put("orderOutList", resList);
            map.put("totalOrderSumMoney", totalOrderSumMoney);
        }
        return map;
    }


    /**
     * @Description: 代付的导入结果进行组装转换
     * @param list - 导入结果集合
     * @return
     * @Author: yoko
     * @Date 2021/7/4 18:05
    */
    public static List<ReplacePayGainResultModel> assembleReplacePayGainResultByExcel(List<OrderOutExcelInModel> list){
        List<ReplacePayGainResultModel> resList = new ArrayList<>();
        for (OrderOutExcelInModel orderOutExcelInModel : list){
            if (!StringUtils.isBlank(orderOutExcelInModel.备注) && !StringUtils.isBlank(orderOutExcelInModel.订单状态)){
                ReplacePayGainResultModel replacePayGainResultModel = new ReplacePayGainResultModel();
                replacePayGainResultModel.setOrderNo(orderOutExcelInModel.备注);
                if (orderOutExcelInModel.订单状态.equals("成功")){
                    replacePayGainResultModel.setTradeStatus(4);
                }else{
                    replacePayGainResultModel.setTradeStatus(2);
                }
                if (!StringUtils.isBlank(orderOutExcelInModel.杉德订单号)){
                    replacePayGainResultModel.setSupplierTradeNo(orderOutExcelInModel.杉德订单号);
                }
                if (!StringUtils.isBlank(orderOutExcelInModel.手续费)){
                    replacePayGainResultModel.setTranFee(orderOutExcelInModel.手续费);
                }
                String remark = "";
                if (!StringUtils.isBlank(orderOutExcelInModel.付款方账号名称)){
                    remark += orderOutExcelInModel.付款方账号名称 + "#";
                }
                if (!StringUtils.isBlank(orderOutExcelInModel.收款方账户)){
                    remark += orderOutExcelInModel.收款方账户 + "#";
                }
                if (!StringUtils.isBlank(orderOutExcelInModel.失败原因)){
                    remark += orderOutExcelInModel.失败原因;
                }
                if (!StringUtils.isBlank(remark)){
                    replacePayGainResultModel.setRemark(remark);
                }
                replacePayGainResultModel.setCurday(DateUtil.getDayNumber(new Date()));
                replacePayGainResultModel.setCurhour(DateUtil.getHour(new Date()));
                replacePayGainResultModel.setCurminute(DateUtil.getCurminute(new Date()));
                resList.add(replacePayGainResultModel);
            }
        }
        return resList;
    }






    public static void main(String[] args) throws Exception {
//        File  file =new File("D:\\tomcatyhk.txt");
//        String  str1 ="您尾号7032卡11月2日00:24工商银行收入(支付宝)1999.99元，余)19.99元，余额1,132.22元，对方户名：李冠朝";
//        System.out.println(str1.split("元").length);

//        BufferedReader in = new BufferedReader(new FileReader("D:\\yhk4.txt"));
//        String str;
//        int  i=0;
//        while ((str = in.readLine()) != null) {
//            String []  dd=str.split("\\)");
//            if(dd.length>=3){
//                System.out.println(i+"==========="+str);
//            }
//            i++;
//
//        }
//        System.out.println(str);
//        List<Long> idList = list.stream().map(ChannelModel::getId).collect(Collectors.toList());// 获取某集合的某属性的集合

        List<OrderOutModel> orderOutList = new ArrayList<>();
        OrderOutModel orderOutModel1 = new OrderOutModel();
        orderOutModel1.setId(1L);
        orderOutModel1.setOrderMoney("1000.00");

        OrderOutModel orderOutModel2 = new OrderOutModel();
        orderOutModel2.setId(2L);
        orderOutModel2.setOrderMoney("2000.00");

        OrderOutModel orderOutModel3 = new OrderOutModel();
        orderOutModel3.setId(3L);
        orderOutModel3.setOrderMoney("3000.00");

        OrderOutModel orderOutModel4 = new OrderOutModel();
        orderOutModel4.setId(4L);
        orderOutModel4.setOrderMoney("4000.00");

        OrderOutModel orderOutModel5 = new OrderOutModel();
        orderOutModel5.setId(5L);
        orderOutModel5.setOrderMoney("5000.00");

        OrderOutModel orderOutModel6 = new OrderOutModel();
        orderOutModel6.setId(6L);
        orderOutModel6.setOrderMoney("6000.00");

        orderOutList.add(orderOutModel1);
        orderOutList.add(orderOutModel2);
        orderOutList.add(orderOutModel3);
        orderOutList.add(orderOutModel4);
        orderOutList.add(orderOutModel5);
        orderOutList.add(orderOutModel6);

        Map<String, Object> map = PublicMethod.getOrderOutByExcelMoneyList(orderOutList, "100.00");
        List<OrderOutModel> resList = new ArrayList<>();
        String totalOrderSumMoney = "";
        if (!map.isEmpty()){
            resList = (List<OrderOutModel>) map.get("orderOutList");
            totalOrderSumMoney = map.get("totalOrderSumMoney").toString();
        }
        for (OrderOutModel orderOutModel : resList){
            log.info("id:" + orderOutModel.getId() + ", orderMoney:" + orderOutModel.getOrderMoney());
        }
        log.info("totalOrderSumMoney:" + totalOrderSumMoney);
    }
}
