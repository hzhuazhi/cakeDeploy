package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.page.BasePage;
import com.xn.common.page.Page;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantDao;
import com.xn.manager.dao.OrderDao;
import com.xn.manager.model.OrderModel;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.OrderService;
import com.xn.manager.util.PublicMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:订单表数据表的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("orderService")
public class OrderServiceImpl<T> extends BaseServiceImpl<T> implements OrderService<T> {
    private static Logger log   =  Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return orderDao;
    }

    @Override
    public List<OrderModel> queryByListRelease(OrderModel orderModel) {
        List<OrderModel>  listRs = new ArrayList<>();
        List<OrderModel>  countlist =orderDao.queryByListReleaseByCount(orderModel);
        orderModel.getPage().setRowCount(countlist.size());
        orderModel.setRowCount(countlist.size());
        List<OrderModel>  list =orderDao.queryByListRelease(orderModel);


        for(OrderModel orderModel1:list){
            String  successRateTask =PublicMethod.accuracy(orderModel1.getSuccessNum()*1.0,orderModel1.getTaskNum()*1.0,2);
            orderModel1.setSuccessRateTask(successRateTask);
            String  successRateMoney =PublicMethod.accuracy(orderModel1.getNumMoney()*1.0,orderModel1.getTaskMoney()*1.0,2);
            orderModel1.setSuccessRateMoney(successRateMoney);
            listRs.add(orderModel1);
        }
        return list;
    }

    @Override
    public List<OrderModel> queryByListReleaseByCount(OrderModel orderModel) {
        return orderDao.queryByListReleaseByCount(orderModel);
    }
}
