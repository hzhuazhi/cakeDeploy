package com.xn.manager.model;

import com.xn.common.page.BasePage;

/**
 * @Description 主卡收款信息的实体属性Bean
 * @Author yoko
 * @Date 2021/2/26 17:02
 * @Version 1.0
 */
public class BankLeadCollectionModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

    /**
     * 归属银行卡ID：对应表tb_fn_bank的主键ID
     *
     * @mbggenerated
     */
    private long bankId;

    /**
     * 订单号：这里订单号可能为空值，因为不一定匹配到订单
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 收款金额
     *
     * @mbggenerated
     */
    private String money;


    /**
     * 卡商信息
     *
     * @mbggenerated
     */
    private String accountNum;

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
     * 创建时间
     *
     * @mbggenerated
     */
    private String createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private String tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private Integer yn;



    /**
     * 银行名称
     *
     * @mbggenerated
     */
    private String bankName;
    /**
     * 银行卡
     *
     * @mbggenerated
     */
    private String bankCard;
    /**
     * 开户人
     *
     * @mbggenerated
     */
    private String accountName;
    /**
     * 卡商id
     *
     * @mbggenerated
     */
    private long accountId;
    private long merchantId;
    private long merchantSiteId;
    /**
     * 卡站点id
     *
     * @mbggenerated
     */
    private long cardSiteId;

    /**
     * 卡商昵称
     *
     * @mbggenerated
     */
    private String accountIdName;

    /**
     * 卡站点昵称
     *
     * @mbggenerated
     */
    private String cardSiteIdName;

    /**
     * 总额度收款金额
     *
     * @mbggenerated
     */
    private String countMoney;
    /**
     * 开始时间
     *
     * @mbggenerated
     */
    private String beginTime;
    /**
     * 关闭时间
     *
     * @mbggenerated
     */
    private String endTime;
}
