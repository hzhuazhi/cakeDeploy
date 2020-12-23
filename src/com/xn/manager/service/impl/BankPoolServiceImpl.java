package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankPoolDao;
import com.xn.manager.model.*;
import com.xn.manager.service.BankPoolService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:银行卡池子的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("bankPoolService")
public class BankPoolServiceImpl<T> extends BaseServiceImpl<T> implements BankPoolService<T> {
    private static Logger log=Logger.getLogger(BankPoolServiceImpl.class);

    @Autowired
    private BankPoolDao bankPoolDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankPoolDao;
    }


    @Override
    public List<BankPoolModel> byIdQueryBank(BankPoolModel bankPoolModel) {
        return bankPoolDao.byIdQueryBank(bankPoolModel);
    }
}
