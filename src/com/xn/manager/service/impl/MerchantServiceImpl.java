package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantDao;
import com.xn.manager.service.MerchantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:卡商扩充数据表的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("merchantService")
public class MerchantServiceImpl<T> extends BaseServiceImpl<T> implements MerchantService<T> {
    private static Logger log   =  Logger.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantDao merchantDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantDao;
    }
}
