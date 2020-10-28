package com.xn.manager.service.impl;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AccountTpDao;
import com.xn.manager.dao.BankDao;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.MobileCardDao;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.model.CardsBankModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.AccountTpService;
import com.xn.manager.service.BankService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:渠道账号银行卡的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("bankService")
public class BankServiceImpl<T> extends BaseServiceImpl<T> implements BankService<T> {
    private static Logger log=Logger.getLogger(BankServiceImpl.class);

    @Autowired
    private BankDao bankDao;


    @Autowired
    private BankTypeDao bankTypeDao;

    @Autowired
    private MobileCardDao mobileCardDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankDao;
    }

    /**
     * @Description: 卡商录入的信息，是否被占用以及，手机号和类型表存在
     * @param bean    银行卡信息
    * @param account   权限归属卡商id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2020/9/12 19:43
     */
    @Override
    public Map<String, Object> isCheckCardsBank(BankModel bean, Account account) {
        Map<String,Object>     map  = new HashMap<>();
        String   type = "0" ;
        BankModel queryBean = new BankModel();
        queryBean.setBankCard(bean.getBankCard());
        BankModel queryBeanRs = (BankModel)bankDao.queryByCondition(queryBean);
        if (queryBeanRs != null && queryBeanRs.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                sendFailureMessage(response,"有重复的银行卡账号,请重新输入其它银行卡账号!");
            map.put("type","1");
            return map;
        }else{
            MobileCardModel mobileCardModel = new MobileCardModel();
            mobileCardModel.setPhoneNum(bean.getPhoneNum());
            MobileCardModel queryMobileBean =(MobileCardModel)mobileCardDao.queryByCondition(mobileCardModel);

            if(queryMobileBean != null && queryMobileBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                BankTypeModel queryBankTypeBean = new BankTypeModel();
                if(null!=bean.getBankTypeId()){
                    if(bean.getBankTypeId()>0){
                        queryBankTypeBean.setId(bean.getBankTypeId());
                    }
                }else if(!StringUtils.isBlank(bean.getBankName())){
                    queryBankTypeBean.setBankName(bean.getBankName());
                }
                BankTypeModel queryBean1 = (BankTypeModel) bankTypeDao.queryByCondition(queryBankTypeBean);
                if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                        cardsBankService.add(bean);
//                        sendSuccessMessage(response, "保存成功~");

                    BankModel bankModel = new BankModel();
                    bankModel.setAlias(bean.getAlias());
                    bankModel.setLastNum(bean.getBankCard().substring(bean.getBankCard().length()-4,bean.getBankCard().length()));
                    bankModel.setMobileCardId(queryMobileBean.getId());

                    if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                        bankModel.setAccountId(account.getId());
                    }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                        bankModel.setAccountId(account.getCreateUser());
                        bankModel.setCardSiteId(account.getId());
                    }
//                    bankModel.setAccountId(account.getId());

                    bankModel.setBankCard(bean.getBankCard());
                    bankModel.setAccountName(bean.getAccountName());
                    bankModel.setInDayMoney(bean.getInDayMoney());
                    bankModel.setInMonthMoney(bean.getInMonthMoney());
                    bankModel.setBankName(queryBean1.getBankName());
                    bankModel.setBankTypeId(queryBean1.getId());
                    bankModel.setBankCode(queryBean1.getBankCode());
                    bankModel.setSmsNum(queryBean1.getSmsNum());
                    bankModel.setPhoneNum(bean.getPhoneNum());
                    map.put("type",type);
                    map.put("bankModel",bankModel);
                    return map;
                }else{
                    map.put("type","3");
                    return map;
//                    return 3;
//                        sendFailureMessage(response,"银行卡类型未部署，请联系管理员进行部署，再进行添加！");
                }
            }else{
                map.put("type","2");
                return map;
//                    sendFailureMessage(response,"手机号未在“手机卡管理”进行部署,请先将手机号部署好再进行添加！");
            }
        }
    }

    @Override
    public List<BankModel> queryAllListImport(BankModel bankModel) {
        return bankDao.queryAllListImport(bankModel);
    }

//    @Override
//    public Map<String, Object> isCheckCardsTypeId(BankModel bean, Account account) {
//        Map<String,Object>     map  = new HashMap<>();
//        String   type = "0" ;
//        BankModel queryBean = new BankModel();
//        queryBean.setBankCard(bean.getBankCard());
//        BankModel queryBeanRs = (BankModel)bankDao.queryByCondition(queryBean);
//        if (queryBeanRs != null && queryBeanRs.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////                sendFailureMessage(response,"有重复的银行卡账号,请重新输入其它银行卡账号!");
//            map.put("type","1");
//            return map;
//        }else{
//            MobileCardModel mobileCardModel = new MobileCardModel();
//            mobileCardModel.setPhoneNum(bean.getPhoneNum());
//            MobileCardModel queryMobileBean =(MobileCardModel)mobileCardDao.queryByCondition(mobileCardModel);
//
//            if(queryMobileBean != null && queryMobileBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                BankTypeModel queryBankTypeBean = new BankTypeModel();
//                queryBankTypeBean.setId(bean.getBankTypeId());
//
//                BankTypeModel queryBean1 = (BankTypeModel) bankTypeDao.queryByCondition(queryBankTypeBean);
//                if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//
//                    BankModel bankModel = new BankModel();
//                    bankModel.setAlias(bean.getAlias());
//                    bankModel.setLastNum(bean.getBankCard().substring(bean.getBankCard().length()-4,bean.getBankCard().length()));
//                    bankModel.setMobileCardId(queryMobileBean.getId());
//
//                    if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
//                        bankModel.setAccountId(account.getId());
//                    }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
//                        bankModel.setAccountId(account.getCreateUser());
//                        bankModel.setCardSiteId(account.getId());
//                    }
////                    bankModel.setAccountId(account.getId());
//                    bankModel.setBankName(bean.getBankName());
//                    bankModel.setBankCard(bean.getBankCard());
//                    bankModel.setAccountName(bean.getAccountName());
//                    bankModel.setInDayMoney(bean.getInDayMoney());
//                    bankModel.setBankTypeId(queryBean1.getId());
//                    bankModel.setInMonthMoney(bean.getInMonthMoney());
//                    bankModel.setBankCode(queryBean1.getBankCode());
//                    bankModel.setSmsNum(queryBean1.getSmsNum());
//                    bankModel.setPhoneNum(bean.getPhoneNum());
//                    map.put("type",type);
//                    map.put("bankModel",bankModel);
//                    return map;
//                }else{
//                    map.put("type","3");
//                    return map;
////                    return 3;
////                        sendFailureMessage(response,"银行卡类型未部署，请联系管理员进行部署，再进行添加！");
//                }
//            }else{
//                map.put("type","2");
//                return map;
////                    sendFailureMessage(response,"手机号未在“手机卡管理”进行部署,请先将手机号部署好再进行添加！");
//            }
//        }
//
//
//
//    }
}
