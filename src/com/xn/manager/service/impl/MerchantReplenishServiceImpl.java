package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantReplenishDao;
import com.xn.manager.model.ChannelReplenishModel;
import com.xn.manager.model.MerchantReplenishModel;
import com.xn.manager.service.MerchantReplenishService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 卡商审核补单申请的Service层的实现层
 * @Author yoko
 * @Date 2020/10/12 18:56
 * @Version 1.0
 */
@Service("merchantReplenishService")
public class MerchantReplenishServiceImpl<T> extends BaseServiceImpl<T> implements MerchantReplenishService<T> {
    private static Logger log=Logger.getLogger(MerchantReplenishServiceImpl.class);

    @Autowired
    private MerchantReplenishDao merchantReplenishDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantReplenishDao;
    }

    @Override
    public int updateCheck(MerchantReplenishModel model) {
        return merchantReplenishDao.updateCheck(model);
    }

    @Override
    public int updateChannelCheck(ChannelReplenishModel model) {
        return merchantReplenishDao.updateChannelCheck(model);
    }
}
