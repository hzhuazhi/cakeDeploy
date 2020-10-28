package com.xn.manager.model;

/**
 * @Description 异常提示信息
 * @Date 2020/9/22 16:29
 * @Version 1.0
 */
public class AbnormalModel {
    /***
     * 异常手机数量
     */
    public Integer   phoneNum;
    /***
     * 异常手机号信息
     */
    public String    phoneInfo;

    /***
     * 异常银行卡信息数量
     */
    public Integer   bankNum;
    /***
     * 异常银行卡信息
     */
    public String   bankInfo;

    /***
     * 异常短信匹配不到模板数量
     */
    public Integer   smsMessageNum;
    /***
     * 异常短信匹配不到信息
     */
    public String   smsMessageInfo;

    /***
     * 未处理打款数量
     */
    public Integer   paymentNum;
    /***
     * 未处理打款信息
     */
    public String   paymentInfo;

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(String phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public Integer getBankNum() {
        return bankNum;
    }

    public void setBankNum(Integer bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public Integer getSmsMessageNum() {
        return smsMessageNum;
    }

    public void setSmsMessageNum(Integer smsMessageNum) {
        this.smsMessageNum = smsMessageNum;
    }

    public String getSmsMessageInfo() {
        return smsMessageInfo;
    }

    public void setSmsMessageInfo(String smsMessageInfo) {
        this.smsMessageInfo = smsMessageInfo;
    }

    public Integer getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(Integer paymentNum) {
        this.paymentNum = paymentNum;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
