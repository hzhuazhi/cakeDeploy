package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.ChannelDao;
import com.xn.manager.service.BankTypeService;
import com.xn.manager.service.ChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:商户信息的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("channelService")
public class ChannelServiceImpl<T> extends BaseServiceImpl<T> implements ChannelService<T> {
    private static Logger log   =  Logger.getLogger(ChannelServiceImpl.class);

    @Autowired
    private ChannelDao channelDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelDao;
    }
}
