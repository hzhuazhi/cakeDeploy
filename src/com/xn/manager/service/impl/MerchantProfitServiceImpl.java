package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantProfitDao;
import com.xn.manager.model.MerchantProfitModel;
import com.xn.manager.service.MerchantProfitService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 卡商的收益数据的Service层的实现层
 * @Author yoko
 * @Date 2020/12/9 11:19
 * @Version 1.0
 */
@Service("merchantProfitService")
public class MerchantProfitServiceImpl<T> extends BaseServiceImpl<T> implements MerchantProfitService<T> {
    private static Logger log=Logger.getLogger(MerchantProfitServiceImpl.class);

    @Autowired
    private MerchantProfitDao merchantProfitDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantProfitDao;
    }

    @Override
    public MerchantProfitModel getTotalData(MerchantProfitModel model) {
        return merchantProfitDao.getTotalData(model);
    }
}
