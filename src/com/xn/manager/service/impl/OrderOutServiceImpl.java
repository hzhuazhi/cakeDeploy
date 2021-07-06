package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantSiteDao;
import com.xn.manager.dao.OrderOutDao;
import com.xn.manager.model.OrderOutModel;
import com.xn.manager.service.MerchantSiteService;
import com.xn.manager.service.OrderOutService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:代付订单的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("orderOutService")
public class OrderOutServiceImpl<T> extends BaseServiceImpl<T> implements OrderOutService<T> {
    private static Logger log   =  Logger.getLogger(OrderOutServiceImpl.class);

    @Autowired
    private OrderOutDao orderOutDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return orderOutDao;
    }

    @Override
    public int updateIsExcel(OrderOutModel model) {
        return orderOutDao.updateIsExcel(model);
    }

    @Override
    public List<OrderOutModel> queryOrderOutByExcelList(OrderOutModel model) {
        return orderOutDao.queryOrderOutByExcelList(model);
    }
}
