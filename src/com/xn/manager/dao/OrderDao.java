package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.OrderModel;

import java.util.List;

/**
 * @Description:订单表
 * @create 2018-09-11 14:42
 **/
public interface OrderDao<T> extends BaseDao<T> {
    /**
     * 每张卡的成功率情况
     * @param orderModel
     * @return
     */
    List<OrderModel> queryByListRelease(OrderModel  orderModel);

    /**
     * 分页
     * @param orderModel
     * @return
     */
    List<OrderModel> queryByListReleaseByCount(OrderModel  orderModel);
}
