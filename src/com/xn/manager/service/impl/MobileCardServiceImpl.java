package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankDao;
import com.xn.manager.dao.MobileCardDao;
import com.xn.manager.service.BankService;
import com.xn.manager.service.MobileCardService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:手机卡的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("mobileCardService")
public class MobileCardServiceImpl<T> extends BaseServiceImpl<T> implements MobileCardService<T> {
    private static Logger log=Logger.getLogger(MobileCardServiceImpl.class);
    @Autowired

    private MobileCardDao<T> mobileCardDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return mobileCardDao;
    }
}
