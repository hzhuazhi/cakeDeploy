package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.InterestWithdrawDao;
import com.xn.manager.service.InterestWithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:分润人提现的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("interestWithdrawService")
public class InterestWithdrawServiceImpl<T> extends BaseServiceImpl<T> implements InterestWithdrawService<T> {
    private static Logger log=Logger.getLogger(InterestWithdrawServiceImpl.class);

    @Autowired
    private InterestWithdrawDao interestWithdrawDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return interestWithdrawDao;
    }

}
