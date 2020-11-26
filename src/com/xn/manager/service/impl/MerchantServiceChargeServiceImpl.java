package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantServiceChargeDao;
import com.xn.manager.service.MerchantServiceChargeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 卡商绑定渠道的手续费的Service层的实现层
 * @Author yoko
 * @Date 2020/11/26 18:54
 * @Version 1.0
 */
@Service("merchantServiceChargeService")
public class MerchantServiceChargeServiceImpl<T> extends BaseServiceImpl<T> implements MerchantServiceChargeService<T> {
    private static Logger log=Logger.getLogger(MerchantServiceChargeServiceImpl.class);

    @Autowired
    private MerchantServiceChargeDao merchantServiceChargeDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantServiceChargeDao;
    }
}
