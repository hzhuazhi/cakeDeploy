package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.OrderModel;

import java.util.List;

/**
 * @Description:订单表数据表的Service层
 * @create 2018-09-11 14:40
 **/
public interface OrderService<T> extends BaseService<T> {
  List<OrderModel> queryByListRelease(OrderModel  orderModel);

  List<OrderModel> queryByListReleaseByCount(OrderModel  orderModel);
}
