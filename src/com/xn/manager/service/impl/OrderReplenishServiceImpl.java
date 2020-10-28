package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.OrderDao;
import com.xn.manager.dao.OrderReplenishDao;
import com.xn.manager.service.OrderReplenishService;
import com.xn.manager.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:订单表数据表的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("orderReplenishService")
public class OrderReplenishServiceImpl<T> extends BaseServiceImpl<T> implements OrderReplenishService<T> {
    private static Logger log   =  Logger.getLogger(OrderReplenishServiceImpl.class);

    @Autowired
    private OrderReplenishDao orderReplenishDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return orderReplenishDao;
    }
}
