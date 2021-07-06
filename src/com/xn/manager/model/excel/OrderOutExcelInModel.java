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
    public String 序号;
    public String 收款方姓名;
    public String 收款方银行卡号;
    public String 金额;
    public String 附言;
    public String 收款人手机号;
    public String 结果;

    public OrderOutExcelInModel(){

    }


    public String get序号() {
        return 序号;
    }

    public void set序号(String 序号) {
        this.序号 = 序号;
    }

    public String get收款方姓名() {
        return 收款方姓名;
    }

    public void set收款方姓名(String 收款方姓名) {
        this.收款方姓名 = 收款方姓名;
    }

    public String get收款方银行卡号() {
        return 收款方银行卡号;
    }

    public void set收款方银行卡号(String 收款方银行卡号) {
        this.收款方银行卡号 = 收款方银行卡号;
    }

    public String get金额() {
        return 金额;
    }

    public void set金额(String 金额) {
        this.金额 = 金额;
    }

    public String get附言() {
        return 附言;
    }

    public void set附言(String 附言) {
        this.附言 = 附言;
    }

    public String get收款人手机号() {
        return 收款人手机号;
    }

    public void set收款人手机号(String 收款人手机号) {
        this.收款人手机号 = 收款人手机号;
    }

    public String get结果() {
        return 结果;
    }

    public void set结果(String 结果) {
        this.结果 = 结果;
    }
}
