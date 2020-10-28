package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankDao;
import com.xn.manager.dao.MobileCardShortMsgDao;
import com.xn.manager.service.BankService;
import com.xn.manager.service.MobileCardShortMsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:手机卡所有短信内容数据的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("mobileCardShortMsgService")
public class MobileCardShortMsgServiceImpl<T> extends BaseServiceImpl<T> implements MobileCardShortMsgService<T> {
    private static Logger log=Logger.getLogger(MobileCardShortMsgServiceImpl.class);
    @Autowired
    private MobileCardShortMsgDao<T> mobileCardShortMsgDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return mobileCardShortMsgDao;
    }
}
