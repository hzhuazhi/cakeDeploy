package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ReplacePayDao;
import com.xn.manager.dao.ReplacePayStrategyDao;
import com.xn.manager.model.ReplacePayStrategyModel;
import com.xn.manager.service.ReplacePayService;
import com.xn.manager.service.ReplacePayStrategyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:配置管理的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("replacePayStrategyService")
public class ReplacePayStrategyServiceImpl<T> extends BaseServiceImpl<T> implements ReplacePayStrategyService<T> {
    private static Logger log=Logger.getLogger(ReplacePayStrategyServiceImpl.class);

    @Autowired
    private ReplacePayStrategyDao replacePayStrategyDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return replacePayStrategyDao;
    }

    @Override
    public ReplacePayStrategyModel queryByTsTime(ReplacePayStrategyModel replacePayStrategyModel) {
        return replacePayStrategyDao.queryByTsTime(replacePayStrategyModel);
    }
}
