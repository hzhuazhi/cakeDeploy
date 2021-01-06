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
     * @Description: 分页-每张卡的成功率情况的数据总数
     * @param model
     * @return
     * @author yoko
     * @date 2020/8/25 20:50
     */
    public int queryByListReleaseCount(OrderModel model);

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
