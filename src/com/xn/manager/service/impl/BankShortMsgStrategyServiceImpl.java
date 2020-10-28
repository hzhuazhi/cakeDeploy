package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankShortMsgStrategyDao;
import com.xn.manager.service.BankShortMsgStrategyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 银行收款短信解析策略的Service层的实现层
 * @Author yoko
 * @Date 2020/9/19 15:05
 * @Version 1.0
 */
@Service("bankShortMsgStrategyService")
public class BankShortMsgStrategyServiceImpl<T> extends BaseServiceImpl<T> implements BankShortMsgStrategyService<T> {
    private static Logger log=Logger.getLogger(StrategyServiceImpl.class);

    @Autowired
    private BankShortMsgStrategyDao bankShortMsgStrategyDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankShortMsgStrategyDao;
    }
}
