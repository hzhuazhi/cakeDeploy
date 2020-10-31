package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantSiteDao;
import com.xn.manager.service.MerchantSiteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:卡商扩充数据表的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("merchantSiteService")
public class MerchantSiteServiceImpl<T> extends BaseServiceImpl<T> implements MerchantSiteService<T> {
    private static Logger log   =  Logger.getLogger(MerchantSiteServiceImpl.class);

    @Autowired
    private MerchantSiteDao merchantSiteDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantSiteDao;
    }
}
