package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantRechargeDao;
import com.xn.manager.dao.WithdrawDao;
import com.xn.manager.service.MerchantRechargeService;
import com.xn.manager.service.WithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:充值订单的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("withdrawService")
public class WithdrawServiceImpl<T> extends BaseServiceImpl<T> implements WithdrawService<T> {
    private static Logger log   =  Logger.getLogger(WithdrawServiceImpl.class);

    @Autowired
    private WithdrawDao withdrawDao;
    @Override
    public BaseDao<T>  getDao() {
        // TODO Auto-generated method stub
        return withdrawDao;
    }
}
