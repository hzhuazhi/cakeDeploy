package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.InterestDao;
import com.xn.manager.dao.InterestMerchantDao;
import com.xn.manager.service.InterestMerchantService;
import com.xn.manager.service.InterestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:利益分配者绑定的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("interestMerchantService")
public class InterestMerchantServiceImpl<T> extends BaseServiceImpl<T> implements InterestMerchantService<T> {
    private static Logger log    = Logger.getLogger(InterestMerchantServiceImpl.class);

    @Autowired
    private InterestMerchantDao interestMerchantDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return interestMerchantDao;
    }

}
