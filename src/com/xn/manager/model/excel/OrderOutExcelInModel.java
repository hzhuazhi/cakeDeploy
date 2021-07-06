package com.xn.manager.model.excel;


import java.io.Serializable;

/**
 * @ClassName:
 * @Description: 代付订单结果导入的字段
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public class OrderOutExcelInModel implements Serializable {
    public String 创建时间;
    public String 客户订单号;
    public String 客户交易名称;
    public String 交易类型;
    public String 交易金额;
    public String 手续费;
    public String 付款方账号;
    public String 付款方账号名称;
    public String 收款方账户;
    public String 收款方名称;
    public String 手续费承担方;
    public String 备注;
    public String 订单状态;
    public String 杉德订单号;
    public String 失败原因;


    public OrderOutExcelInModel(){

    }


    public String get创建时间() {
        return 创建时间;
    }

    public void set创建时间(String 创建时间) {
        this.创建时间 = 创建时间;
    }

    public String get客户订单号() {
        return 客户订单号;
    }

    public void set客户订单号(String 客户订单号) {
        this.客户订单号 = 客户订单号;
    }

    public String get客户交易名称() {
        return 客户交易名称;
    }

    public void set客户交易名称(String 客户交易名称) {
        this.客户交易名称 = 客户交易名称;
    }

    public String get交易类型() {
        return 交易类型;
    }

    public void set交易类型(String 交易类型) {
        this.交易类型 = 交易类型;
    }

    public String get交易金额() {
        return 交易金额;
    }

    public void set交易金额(String 交易金额) {
        this.交易金额 = 交易金额;
    }

    public String get付款方账号() {
        return 付款方账号;
    }

    public void set付款方账号(String 付款方账号) {
        this.付款方账号 = 付款方账号;
    }

    public String get付款方账号名称() {
        return 付款方账号名称;
    }

    public void set付款方账号名称(String 付款方账号名称) {
        this.付款方账号名称 = 付款方账号名称;
    }

    public String get收款方账户() {
        return 收款方账户;
    }

    public void set收款方账户(String 收款方账户) {
        this.收款方账户 = 收款方账户;
    }

    public String get收款方名称() {
        return 收款方名称;
    }

    public void set收款方名称(String 收款方名称) {
        this.收款方名称 = 收款方名称;
    }

    public String get手续费承担方() {
        return 手续费承担方;
    }

    public void set手续费承担方(String 手续费承担方) {
        this.手续费承担方 = 手续费承担方;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }

    public String get订单状态() {
        return 订单状态;
    }

    public void set订单状态(String 订单状态) {
        this.订单状态 = 订单状态;
    }

    public String get杉德订单号() {
        return 杉德订单号;
    }

    public void set杉德订单号(String 杉德订单号) {
        this.杉德订单号 = 杉德订单号;
    }

    public String get失败原因() {
        return 失败原因;
    }

    public void set失败原因(String 失败原因) {
        this.失败原因 = 失败原因;
    }

    public String get手续费() {
        return 手续费;
    }

    public void set手续费(String 手续费) {
        this.手续费 = 手续费;
    }
}
