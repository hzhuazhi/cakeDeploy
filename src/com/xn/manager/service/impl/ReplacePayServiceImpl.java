package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ReplacePayDao;
import com.xn.manager.service.ReplacePayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:渠道账号银行卡的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("replacePayService")
public class ReplacePayServiceImpl<T> extends BaseServiceImpl<T> implements ReplacePayService<T> {
    private static Logger log=Logger.getLogger(ReplacePayServiceImpl.class);

    @Autowired
    private ReplacePayDao replacePayDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return replacePayDao;
    }


}
