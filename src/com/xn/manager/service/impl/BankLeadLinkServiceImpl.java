package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankLeadLinkDao;
import com.xn.manager.service.BankLeadLinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 主卡与设备卡关联关系的Service层的实现层
 * @Author yoko
 * @Date 2021/2/26 16:48
 * @Version 1.0
 */
@Service("bankLeadLinkService")
public class BankLeadLinkServiceImpl<T> extends BaseServiceImpl<T> implements BankLeadLinkService<T> {
    private static Logger log=Logger.getLogger(BankLeadLinkServiceImpl.class);

    @Autowired
    private BankLeadLinkDao bankLeadLinkDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankLeadLinkDao;
    }
}
