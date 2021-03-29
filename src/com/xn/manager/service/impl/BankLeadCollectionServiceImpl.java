package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankLeadCollectionDao;
import com.xn.manager.service.BankLeadCollectionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 主卡收款信息的Service层的实现层
 * @Author yoko
 * @Date 2021/2/26 16:48
 * @Version 1.0
 */
@Service("bankLeadCollectionService")
public class BankLeadCollectionServiceImpl<T> extends BaseServiceImpl<T> implements BankLeadCollectionService<T> {
    private static Logger log=Logger.getLogger(BankLeadCollectionServiceImpl.class);

    @Autowired
    private BankLeadCollectionDao bankLeadCollectionDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankLeadCollectionDao;
    }
}
