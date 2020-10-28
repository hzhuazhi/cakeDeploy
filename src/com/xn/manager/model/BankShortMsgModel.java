package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class BankShortMsgModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 订单号：对应表tb_fr_order里面的订单号：后续补充进来的
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 归属手机号ID：对应表tb_fn_mobile_card的主键ID
     *
     * @mbggenerated
     */
    private Long mobileCardId;

    /**
     * 回调数据的手机号
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * 归属银行卡ID：对应表tb_fn_bank的主键ID
     *
     * @mbggenerated
     */
    private Long bankId;

    /**
     * 银行卡归属类型：对应表tb_fr_bank_type的主键ID
     *
     * @mbggenerated
     */
    private Long bankTypeId;

    /**
     * 回调对照凭证短信来源:例如招商银行的是95555
     *
     * @mbggenerated
     */
    private String smsNum;

    /**
     * 回调对照凭证内容：短信的全部内容
            例如：【南京银行】您尾号5298的账号与10月10日15时11份收到由无锡公司汇入的300.00元。
            这里的凭证内容：这里内容是多个内容，以英文逗号进行隔开。例如：【南京银行】您尾号5
     *
     * @mbggenerated
     */
    private String smsContent;

    /**
     * 收款金额
     *
     * @mbggenerated
     */
    private String money;

    /**
     * 回调对照凭证银行卡尾号：收到短信后，短信内容尾号xxxx收款
     *
     * @mbggenerated
     */
    private String lastNum;

    /**
     * 订单状态：1初始化，2失败，3脏数据，4成功
     *
     * @mbggenerated
     */
    private Integer orderStatus;

    /**
     * 补充数据的类型：1初始化，2补充数据失败（未匹配到银行卡的数据等..），3补充数据成功
     *
     * @mbggenerated
     */
    private Integer workType;

    /**
     * 数据说明：做解说用的
     *
     * @mbggenerated
     */
    private String dataExplain;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 创建日期：存的日期格式20160530
     *
     * @mbggenerated
     */
    private Integer curday;

    /**
     * 创建所属小时：24小时制
     *
     * @mbggenerated
     */
    private Integer curhour;

    /**
     * 创建所属分钟：60分钟制
     *
     * @mbggenerated
     */
    private Integer curminute;

    /**
     * 运行计算次数
     *
     * @mbggenerated
     */
    private Integer runNum;

    /**
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private Integer runStatus;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private Date tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private Integer yn;
    /**
     * 是否处理：1、未处理 2、已处理
     *
     * @mbggenerated
     */
    private Integer handleType;


    /**
     * 银行卡号
     *
     * @mbggenerated
     */
    private String bankCard;

    /**
     * 银行名称
     *
     * @mbggenerated
     */

    private String bankName;
    /**
     * 归属人
     *
     * @mbggenerated
     */
    private String accountName;
    /**
     * 卡商
     *
     * @mbggenerated
     */
    private String alias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getMobileCardId() {
        return mobileCardId;
    }

    public void setMobileCardId(Long mobileCardId) {
        this.mobileCardId = mobileCardId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(Long bankTypeId) {
        this.bankTypeId = bankTypeId;
    }

    public String getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(String smsNum) {
        this.smsNum = smsNum;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLastNum() {
        return lastNum;
    }

    public void setLastNum(String lastNum) {
        this.lastNum = lastNum;
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

    public Integer getCurday() {
        return curday;
    }

    public void setCurday(Integer curday) {
        this.curday = curday;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTsTime() {
        return tsTime;
    }

    public void setTsTime(Date tsTime) {
        this.tsTime = tsTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Integer getCurhour() {
        return curhour;
    }

    public void setCurhour(Integer curhour) {
        this.curhour = curhour;
    }

    public Integer getCurminute() {
        return curminute;
    }

    public void setCurminute(Integer curminute) {
        this.curminute = curminute;
    }

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Integer getHandleType() {
        return handleType;
    }

    public void setHandleType(Integer handleType) {
        this.handleType = handleType;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}