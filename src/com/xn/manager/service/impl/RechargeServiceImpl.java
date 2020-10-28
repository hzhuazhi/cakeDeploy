package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.RechargeDao;
import com.xn.manager.model.RechargeModel;
import com.xn.manager.service.RechargeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 卡商充值的Service层的实现层
 * @Author yoko
 * @Date 2020/9/28 18:12
 * @Version 1.0
 */
@Service("rechargeService")
public class RechargeServiceImpl<T> extends BaseServiceImpl<T> implements RechargeService<T> {
    private static Logger log=Logger.getLogger(IssueServiceImpl.class);

    @Autowired
    private RechargeDao rechargeDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return rechargeDao;
    }

    @Override
    public int updateOrderStatus(RechargeModel model) {
        return rechargeDao.updateOrderStatus(model);
    }

    @Override
    public int updateCheck(RechargeModel model) {
        return rechargeDao.updateCheck(model);
    }

    @Override
    public int updateCardSite(RechargeModel model) {
        return rechargeDao.updateCardSite(model);
    }

    @Override
    public int updateOperateStatus(RechargeModel model) {
        return rechargeDao.updateOperateStatus(model);
    }
}
