package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankLeadDao;
import com.xn.manager.service.BankLeadService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 主卡：原始卡卡信息的Service层的实现层
 * @Author yoko
 * @Date 2021/2/26 16:48
 * @Version 1.0
 */
@Service("bankLeadService")
public class BankLeadServiceImpl<T> extends BaseServiceImpl<T> implements BankLeadService<T> {
    private static Logger log=Logger.getLogger(BankLeadServiceImpl.class);

    @Autowired
    private BankLeadDao bankLeadDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankLeadDao;
    }
}
