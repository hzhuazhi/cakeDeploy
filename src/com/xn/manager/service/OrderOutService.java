package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.OrderModel;
import com.xn.manager.model.OrderOutModel;

import java.util.List;

/**
 * @Description:代付订单表的Service层
 * @create 2018-09-11 14:40
 **/
public interface OrderOutService<T> extends BaseService<T> {

    /**
     * @Description: 修改代付订单的导出状态
     * @param model
     * @return
     * @Author: yoko
     * @Date 2021/7/3 18:01
    */
    public int updateIsExcel(OrderOutModel model);


    /**
     * @Description: 查询导出的代付订单集合
     * @param model
     * @return
     * @Author: yoko
     * @Date 2021/7/3 18:02
    */
    public List<OrderOutModel> queryOrderOutByExcelList(OrderOutModel model);
}
