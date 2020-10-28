package com.xn.manager.model.excel;

/**
 * @Description Excel 导入进来的Bank表
 * @Date 2020/9/13 12:02
 * @Version 1.0
 */
public class BankExcelModel {
    /**
     * 别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 手机号
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * 银行名称/归属开户行
     *
     * @mbggenerated
     */
    private String bankName;

    /**
     * 银行卡账号/银行卡号
     *
     * @mbggenerated
     */
    private String bankCard;


    /**
     * 开户名
     *
     * @mbggenerated
     */
    private String accountName;

    /**
     * 收款日限金额
     *
     * @mbggenerated
     */
    private String inDayMoney;

    /**
     * 收款月限金额
     *
     * @mbggenerated
     */
    private String inMonthMoney;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getInMonthMoney() {
        return inMonthMoney;
    }

    public void setInMonthMoney(String inMonthMoney) {
        this.inMonthMoney = inMonthMoney;
    }

    public String getInDayMoney() {
        return inDayMoney;
    }

    public void setInDayMoney(String inDayMoney) {
        this.inDayMoney = inDayMoney;
    }
}
