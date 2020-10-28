package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.service.BankTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:银行卡类型的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("bankTypeService")
public class BankTypeServiceImpl<T> extends BaseServiceImpl<T> implements BankTypeService<T> {
    private static Logger log   =  Logger.getLogger(BankTypeServiceImpl.class);

    @Autowired
    private BankTypeDao<T> bankTypeDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankTypeDao;
    }
}
