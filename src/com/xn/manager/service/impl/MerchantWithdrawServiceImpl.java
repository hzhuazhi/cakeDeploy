package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.InterestDao;
import com.xn.manager.dao.MerchantWithdrawDao;
import com.xn.manager.service.InterestService;
import com.xn.manager.service.MerchantWithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:卡商提现的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("merchantWithdrawService")
public class MerchantWithdrawServiceImpl<T> extends BaseServiceImpl<T> implements MerchantWithdrawService<T> {
    private static Logger log=Logger.getLogger(MerchantWithdrawServiceImpl.class);

    @Autowired
    private MerchantWithdrawDao merchantWithdrawDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantWithdrawDao;
    }

}
