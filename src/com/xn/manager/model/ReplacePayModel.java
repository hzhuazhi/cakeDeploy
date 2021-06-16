package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;
import java.util.List;

public class ReplacePayModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;
    private List<Long> idList;

    /**
     * 别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 公司名称
     *
     * @mbggenerated
     */
    private String companyName;

    /**
     * 公司地址
     *
     * @mbggenerated
     */
    private String companyAds;

    /**
     * 银行卡归属类型：对应表tb_fr_bank_type的主键ID
     *
     * @mbggenerated
     */
    private Long bankTypeId;

    /**
     * 归属卡商ID：对应表tb_fr_merchant的主键ID，并且角色是卡商
     *
     * @mbggenerated
     */
    private Long merchantId;

    /**
     * 银行卡归属卡站点ID：对应表tb_fr_merchant_site的主键ID，并且角色是卡站点
     *
     * @mbggenerated
     */
    private Long merchantSiteId;

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
     * 银行支行/支行名称
     *
     * @mbggenerated
     */
    private String subbranchName;

    /**
     * 开户名
     *
     * @mbggenerated
     */
    private String accountName;

    /**
     * 余额：银行卡现有余额
     *
     * @mbggenerated
     */
    private String balance;

    /**
     * 可用余额
     *
     * @mbggenerated
     */
    private String useBalance;

    /**
     * 出码接口地址：请求的地址
     *
     * @mbggenerated
     */
    private String outInterfaceAds;

    /**
     * 回调地址：这个回调接口地址可能是我方的地址（被动接收），也有可能是对方的地址（主动请求）
     *
     * @mbggenerated
     */
    private String inInterfaceAds;

    /**
     * 查询余额接口地址
     *
     * @mbggenerated
     */
    private String balanceInterfaceAds;

    /**
     * 商户ID、商户号
     *
     * @mbggenerated
     */
    private String businessNum;

    /**
     * 平台商户ID、平台商户号
     *
     * @mbggenerated
     */
    private String platformBusinessNum;

    /**
     * 账户属性 ： 0-对私   1-对公
     *
     * @mbggenerated
     */
    private String accountAttribute;

    /**
     * 账号类型 ： 3-公司账户  4-银行卡
     *
     * @mbggenerated
     */
    private String accountType;

    /**
     * 公司负责人电话
     *
     * @mbggenerated
     */
    private String telephoneNum;

    /**
     * 公钥
     *
     * @mbggenerated
     */
    private String publicKey;

    /**
     * 公钥路径
     *
     * @mbggenerated
     */
    private String publicKeyPath;

    /**
     * 私钥
     *
     * @mbggenerated
     */
    private String privateKey;

    /**
     * 私钥路径
     *
     * @mbggenerated
     */
    private String privateKeyPath;

    /**
     * 衫德秘钥
     *
     * @mbggenerated
     */
    private String sandKey;

    /**
     * 衫德秘钥路径
     *
     * @mbggenerated
     */
    private String sandKeyPath;

    /**
     * 版本号
     *
     * @mbggenerated
     */
    private String versionNum;

    /**
     * 付款类型/产品ID：1付款对私，2付款对公
     *
     * @mbggenerated
     */
    private Integer payType;

    /**
     * 获取订单结果类型：1被动接收数据，2主动查询
     *
     * @mbggenerated
     */
    private Integer gainDataType;

    /**
     * 主动获取订单结果的间隔时间类型：1任意时间都可查询，2固定时间，3集合某时间间隔（5分钟，8分钟....）
     *
     * @mbggenerated
     */
    private Integer gainDataTimeType;

    /**
     * 主动获取订单结果的具体间隔时间（单位秒）：当gain_data_time_type=1时这里无需填值，等于2时这里可以填任意正整数，等于3时这里填（300#800#...多个以#隔开）
     *
     * @mbggenerated
     */
    private String gainDataTime;

    /**
     * 数据说明：检测被限制的原因:task跑日月总限制，如果被限制，连续给出订单失败会填充被限制的原因
     *
     * @mbggenerated
     */
    private String dataExplain;

    /**
     * 是否测试通过：1未通过，2通过；收到银行卡短信，并且解析短信模板配置正确
     *
     * @mbggenerated
     */
    private Integer isOk;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 使用状态:1初始化有效正常使用，2无效暂停使用
     *
     * @mbggenerated
     */
    private Integer useStatus;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAds() {
        return companyAds;
    }

    public void setCompanyAds(String companyAds) {
        this.companyAds = companyAds;
    }

    public Long getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(Long bankTypeId) {
        this.bankTypeId = bankTypeId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantSiteId() {
        return merchantSiteId;
    }

    public void setMerchantSiteId(Long merchantSiteId) {
        this.merchantSiteId = merchantSiteId;
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

    public String getSubbranchName() {
        return subbranchName;
    }

    public void setSubbranchName(String subbranchName) {
        this.subbranchName = subbranchName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(String useBalance) {
        this.useBalance = useBalance;
    }

    public String getOutInterfaceAds() {
        return outInterfaceAds;
    }

    public void setOutInterfaceAds(String outInterfaceAds) {
        this.outInterfaceAds = outInterfaceAds;
    }

    public String getInInterfaceAds() {
        return inInterfaceAds;
    }

    public void setInInterfaceAds(String inInterfaceAds) {
        this.inInterfaceAds = inInterfaceAds;
    }

    public String getBalanceInterfaceAds() {
        return balanceInterfaceAds;
    }

    public void setBalanceInterfaceAds(String balanceInterfaceAds) {
        this.balanceInterfaceAds = balanceInterfaceAds;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getPlatformBusinessNum() {
        return platformBusinessNum;
    }

    public void setPlatformBusinessNum(String platformBusinessNum) {
        this.platformBusinessNum = platformBusinessNum;
    }

    public String getAccountAttribute() {
        return accountAttribute;
    }

    public void setAccountAttribute(String accountAttribute) {
        this.accountAttribute = accountAttribute;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getSandKey() {
        return sandKey;
    }

    public void setSandKey(String sandKey) {
        this.sandKey = sandKey;
    }

    public String getSandKeyPath() {
        return sandKeyPath;
    }

    public void setSandKeyPath(String sandKeyPath) {
        this.sandKeyPath = sandKeyPath;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getGainDataTime() {
        return gainDataTime;
    }

    public void setGainDataTime(String gainDataTime) {
        this.gainDataTime = gainDataTime;
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


    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getGainDataType() {
        return gainDataType;
    }

    public void setGainDataType(Integer gainDataType) {
        this.gainDataType = gainDataType;
    }

    public Integer getGainDataTimeType() {
        return gainDataTimeType;
    }

    public void setGainDataTimeType(Integer gainDataTimeType) {
        this.gainDataTimeType = gainDataTimeType;
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}