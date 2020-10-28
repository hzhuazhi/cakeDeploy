package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.MerchantRechargeDao;
import com.xn.manager.service.BankTypeService;
import com.xn.manager.service.MerchantRechargeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:卡商充值记录的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("merchantRechargeService")
public class MerchantRechargeServiceImpl<T> extends BaseServiceImpl<T> implements MerchantRechargeService<T> {
    private static Logger log   =  Logger.getLogger(MerchantRechargeServiceImpl.class);

    @Autowired
    private MerchantRechargeDao merchantRechargeDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantRechargeDao;
    }
}
