package com.xn.manager.model.replacepay;



import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 第三方代付主动拉取结果返回的订单结果的实体属性Bean
 * @Author yoko
 * @Date 2021/6/16 17:07
 * @Version 1.0
 */
public class ReplacePayGainResultModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 3203223201135L;

    public ReplacePayGainResultModel(){

    }

    /**
     * 主键ID
     */
    private long id;

    /**
     * 归属代付资源ID：对应表tb_fr_replace_pay的主键ID
     */
    private long replacePayId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 交易时间：时间戳20210607102058
     */
    private long tradeTime;

    /**
     * 上游订单号：供应商订单号、对接放的订单号
     */
    private String supplierTradeNo;

    /**
     * 手续费
     */
    private String tranFee;

    /**
     * 交易状态:1初始化，2交易失败，3处理中，4交易成功
     */
    private int tradeStatus;

    /**
     * 额外手续费
     */
    private String extraFee;

    /**
     * 节假日手续费
     */
    private String holidayFee;

    /**
     * 数据说明：做解说用的
     */
    private String dataExplain;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;

    /**
     * 创建所属小时：24小时制
     */
    private int curhour;

    /**
     * 创建所属分钟：60分钟制
     */
    private int curminute;

    /**
     *运行计算次数
     */
    private int runNum;

    /**
     * 运行计算状态：0初始化，1锁定，2计算失败，3计算成功
     */
    private int runStatus;

    /**
     * 发送次数
     */
    private int sendNum;

    /**
     * 发送状态：0初始化，1锁定，2计算失败，3计算成功
     */
    private int sendStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否有效：0有效，1无效/删除
     */
    private int yn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReplacePayId() {
        return replacePayId;
    }

    public void setReplacePayId(long replacePayId) {
        this.replacePayId = replacePayId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getSupplierTradeNo() {
        return supplierTradeNo;
    }

    public void setSupplierTradeNo(String supplierTradeNo) {
        this.supplierTradeNo = supplierTradeNo;
    }

    public String getTranFee() {
        return tranFee;
    }

    public void setTranFee(String tranFee) {
        this.tranFee = tranFee;
    }

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(String extraFee) {
        this.extraFee = extraFee;
    }

    public String getHolidayFee() {
        return holidayFee;
    }

    public void setHolidayFee(String holidayFee) {
        this.holidayFee = holidayFee;
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getCurhour() {
        return curhour;
    }

    public void setCurhour(int curhour) {
        this.curhour = curhour;
    }

    public int getCurminute() {
        return curminute;
    }

    public void setCurminute(int curminute) {
        this.curminute = curminute;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }
}
